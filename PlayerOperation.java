
package com.tka23;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerOperation {

 
    private List<Player> getPlayers(String sql, Object... values) {

        List<Player> list = new ArrayList<>();

        try (Connection con = MyDbConfig.getSQLConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
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
            System.out.println("Database error: " + e.getMessage());
        }

        return list;
    }

    
    private void executeUpdate(String sql, Object... values) {

        try (Connection con = MyDbConfig.getSQLConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }

            int rows = ps.executeUpdate();
            System.out.println("Rows affected: " + rows);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

   
    private void showResult(String sql, Object... values) {

        try (Connection con = MyDbConfig.getSQLConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Result: " + rs.getDouble(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    
    public List<Player> getAllPlayers() {
        return getPlayers("SELECT * FROM player");
    }

    
    public List<Player> getPlayersByTeam(String team) {
        return getPlayers(
            "SELECT * FROM player WHERE TeamName = ?", team);
    }

    
    public List<Player> getPlayersByCategory(String category) {
        return getPlayers(
            "SELECT * FROM player WHERE Category = ?", category);
    }

    
    public List<Player> getPlayerById(int id) {
        return getPlayers(
            "SELECT * FROM player WHERE Playerid = ?", id);
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
        showResult("SELECT COUNT(*) FROM player");
    }


    public void countPlayersByTeam(String team) {
        showResult(
            "SELECT COUNT(*) FROM player WHERE TeamName = ?", team);
    }

    
    public void countPlayersByCategory(String category) {
        showResult(
            "SELECT COUNT(*) FROM player WHERE Category = ?", category);
    }

    
    public void getTotalRunsByTeam(String team) {
        showResult(
            "SELECT COALESCE(SUM(Playerruns), 0) FROM player " +
            "WHERE TeamName = ?", team);
    }

    
    public void getAverageRunsByTeam(String team) {
        showResult(
            "SELECT COALESCE(AVG(Playerruns), 0) FROM player " +
            "WHERE TeamName = ?", team);
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
        executeUpdate(
            "INSERT INTO player " +
            "(Playerid, Playername, TeamName, Playerruns, " +
            "Playercatches, Playerwickets, Category) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)",
            p.getPlayerid(),
            p.getPlayername(),
            p.getTeamName(),
            p.getPlayerruns(),
            p.getPlayercatches(),
            p.getPlayerwickets(),
            p.getCategory()
        );
    }

    
    public void updateRuns(int id, int runs) {
        executeUpdate(
            "UPDATE player SET Playerruns = ? WHERE Playerid = ?",
            runs, id);
    }

   
    public void updateCatches(int id, int catches) {
        executeUpdate(
            "UPDATE player SET Playercatches = ? WHERE Playerid = ?",
            catches, id);
    }

    public void updateWickets(int id, int wickets) {
        executeUpdate(
            "UPDATE player SET Playerwickets = ? WHERE Playerid = ?",
            wickets, id);
    }

   
    public void updateTeam(int id, String team) {
        executeUpdate(
            "UPDATE player SET TeamName = ? WHERE Playerid = ?",
            team, id);
    }

    
    public void deletePlayer(int id) {
        executeUpdate(
            "DELETE FROM player WHERE Playerid = ?", id);
    }

   
    public List<Player> sortPlayersByRuns() {
        return getPlayers(
            "SELECT * FROM player ORDER BY Playerruns DESC");
    }
}
