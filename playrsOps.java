
package com.tka23;

import java.sql.*;
import java.util.*;

public class playrsOps {

    
    public List<Player> getAllPlayers() {
        return getPlayers(
                "SELECT * FROM player ORDER BY Playerid");
    }

    
    public List<Player> getPlayersByTeam(String team) {
        return getPlayers(
                "SELECT * FROM player WHERE TeamName=? ORDER BY Playerid",
                team);
    }

    
    public List<Player> getPlayersByCategory(String category) {
        return getPlayers(
                "SELECT * FROM player WHERE Category=?",
                category);
    }

   
    public List<Player> getPlayerById(int id) {
        return getPlayers(
                "SELECT * FROM player WHERE Playerid=?", id);
    }

    
    public List<Player> searchPlayerByName(String name) {
        return getPlayers(
                "SELECT * FROM player WHERE Playername LIKE ?",
                "%" + name + "%");
    }

    
    public List<Player> getHighestRunPlayer() {
        return getPlayers(
                "SELECT * FROM player WHERE Playerruns = " +
                "(SELECT MAX(Playerruns) FROM player)");
    }

    
    public List<Player> getLowestRunPlayer() {
        return getPlayers(
                "SELECT * FROM player WHERE Playerruns = " +
                "(SELECT MIN(Playerruns) FROM player)");
    }

    
    public List<Player> getTop5RunPlayers() {
        return getPlayers(
                "SELECT * FROM player ORDER BY Playerruns DESC LIMIT 5");
    }

    
    public List<Player> getHighestCatchPlayer() {
        return getPlayers(
                "SELECT * FROM player WHERE Playercatches = " +
                "(SELECT MAX(Playercatches) FROM player)");
    }

    
    public List<Player> getHighestWicketPlayer() {
        return getPlayers(
                "SELECT * FROM player WHERE Playerwickets = " +
                "(SELECT MAX(Playerwickets) FROM player)");
    }

    
    public void getTotalPlayers() {
        showNumber("SELECT COUNT(*) FROM player");
    }

   
    public void countPlayersByTeam(String team) {
        showNumber(
                "SELECT COUNT(*) FROM player WHERE TeamName=?", team);
    }

    
    public void countPlayersByCategory(String category) {
        showNumber(
                "SELECT COUNT(*) FROM player WHERE Category=?", category);
    }

   
    public void getTotalRunsByTeam(String team) {
        showNumber(
                "SELECT COALESCE(SUM(Playerruns),0) FROM player " +
                "WHERE TeamName=?", team);
    }

   
    public void getAverageRunsByTeam(String team) {
        showNumber(
                "SELECT COALESCE(AVG(Playerruns),0) FROM player " +
                "WHERE TeamName=?", team);
    }

    
    public List<Player> getPlayersAboveRuns(int runs) {
        return getPlayers(
                "SELECT * FROM player WHERE Playerruns > ? " +
                "ORDER BY Playerruns DESC", runs);
    }

    
    public List<Player> getPlayersAboveWickets(int wickets) {
        return getPlayers(
                "SELECT * FROM player WHERE Playerwickets > ? " +
                "ORDER BY Playerwickets DESC", wickets);
    }

    
    public List<Player> getPlayersAboveCatches(int catches) {
        return getPlayers(
                "SELECT * FROM player WHERE Playercatches > ? " +
                "ORDER BY Playercatches DESC", catches);
    }

    
    public void addPlayer(Player p) {
        String sql = "INSERT INTO player " +
                "(Playerid, Playername, TeamName, Playerruns, " +
                "Playercatches, Playerwickets, Category) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = MyDbConfig.getSQLConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getPlayerid());
            ps.setString(2, p.getPlayername());
            ps.setString(3, p.getTeamName());
            ps.setInt(4, p.getPlayerruns());
            ps.setInt(5, p.getPlayercatches());
            ps.setInt(6, p.getPlayerwickets());
            ps.setString(7, p.getCategory());

            int rows = ps.executeUpdate();
            System.out.println(rows + " player inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    
    public void updateRuns(int id, int runs) {
        executeUpdate(
                "UPDATE player SET Playerruns=? WHERE Playerid=?",
                runs, id);
    }

    
    public void updateCatches(int id, int catches) {
        executeUpdate(
                "UPDATE player SET Playercatches=? WHERE Playerid=?",
                catches, id);
    }

    
    public void updateWickets(int id, int wickets) {
        executeUpdate(
                "UPDATE player SET Playerwickets=? WHERE Playerid=?",
                wickets, id);
    }

    
    public void updateTeam(int id, String team) {
        executeUpdate(
                "UPDATE player SET TeamName=? WHERE Playerid=?",
                team, id);
    }

  
    public void deletePlayer(int id) {
        executeUpdate(
                "DELETE FROM player WHERE Playerid=?", id);
    }


    public List<Player> sortPlayersByRuns() {
        return getPlayers(
                "SELECT * FROM player ORDER BY Playerruns DESC");
    }

    
    private List<Player> getPlayers(String sql, Object... params) {
        List<Player> list = new ArrayList<>();

        try (Connection con = MyDbConfig.getSQLConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Player p = new Player(
                            rs.getInt("Playerid"),
                            rs.getString("Playername"),
                            rs.getString("TeamName"),
                            rs.getInt("Playerruns"),
                            rs.getInt("Playercatches"),
                            rs.getInt("Playerwickets"),
                            rs.getString("Category")
                    );

                    list.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }

        return list;
    }

    
    private void showNumber(String sql, Object... params) {
        try (Connection con = MyDbConfig.getSQLConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Result: " + rs.getDouble(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

   
    private void executeUpdate(String sql, Object... params) {
        try (Connection con = MyDbConfig.getSQLConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Operation successful. Rows affected: " + rows);
            } else {
                System.out.println("No matching player found or no rows changed.");
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed: " + e.getMessage());
        }
    }
}
