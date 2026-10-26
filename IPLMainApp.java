
package com.tka23;

import java.util.List;

public class IPLMainApp {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("   WELCOME TO IPL MANAGEMENT SYSTEM");
        System.out.println("======================================");

        PlayerOperation ops = new PlayerOperation();

        
        System.out.println("\n1. ALL PLAYERS FROM ALL TEAMS");
        printPlayers(ops.getAllPlayers());

        
        System.out.println("\n2. PLAYERS OF MI TEAM");
        printPlayers(ops.getPlayersByTeam("MI"));

        
        System.out.println("\n3. PLAYERS BY CATEGORY: Batsman");
        printPlayers(ops.getPlayersByCategory("Batsman"));

        
        System.out.println("\n4. PLAYER WITH ID 1");
        printPlayers(ops.getPlayerById(1));

        
        System.out.println("\n5. SEARCH PLAYER: Virat");
        printPlayers(ops.searchPlayerByName("Virat"));

        
        System.out.println("\n6. PLAYER WITH HIGHEST RUNS");
        printPlayers(ops.getHighestRunPlayer());

        
        System.out.println("\n7. PLAYER WITH LOWEST RUNS");
        printPlayers(ops.getLowestRunPlayer());

        
        System.out.println("\n8. TOP FIVE PLAYERS BY RUNS");
        printPlayers(ops.getTop5RunPlayers());

        
        System.out.println("\n9. PLAYER WITH HIGHEST CATCHES");
        printPlayers(ops.getHighestCatchPlayer());

        
        System.out.println("\n10. PLAYER WITH HIGHEST WICKETS");
        printPlayers(ops.getHighestWicketPlayer());

        
        System.out.println("\n11. TOTAL NUMBER OF PLAYERS");
        ops.getTotalPlayers();

        
        System.out.println("\n12. NUMBER OF PLAYERS IN MI");
        ops.countPlayersByTeam("MI");

        
        System.out.println("\n13. NUMBER OF BATSMEN");
        ops.countPlayersByCategory("Batsman");

        
        System.out.println("\n14. TOTAL RUNS OF MI TEAM");
        ops.getTotalRunsByTeam("MI");
        

        System.out.println("\n15. AVERAGE RUNS OF MI TEAM");
        ops.getAverageRunsByTeam("MI");

        
        System.out.println("\n16. PLAYERS WITH MORE THAN 3000 RUNS");
        printPlayers(ops.getPlayersAboveRuns(3000));

        
        System.out.println("\n17. PLAYERS WITH MORE THAN 50 WICKETS");
        printPlayers(ops.getPlayersAboveWickets(50));

        
        System.out.println("\n18. PLAYERS WITH MORE THAN 30 CATCHES");
        printPlayers(ops.getPlayersAboveCatches(30));


        
        System.out.println("\n19. ADD NEW PLAYER");

        Player newPlayer = new Player(
            100, "Test Player", "GT", 1000, 20, 10, "Batsman"
        );

        ops.addPlayer(newPlayer);
        printPlayers(ops.getPlayerById(100));


        
        System.out.println("\n20. UPDATE PLAYER RUNS");

        ops.updateRuns(100, 1500);
        printPlayers(ops.getPlayerById(100));


        
        System.out.println("\n21. UPDATE PLAYER CATCHES");

        ops.updateCatches(100, 25);
        printPlayers(ops.getPlayerById(100));


        
        System.out.println("\n22. UPDATE PLAYER WICKETS");

        ops.updateWickets(100, 15);
        printPlayers(ops.getPlayerById(100));


        
        System.out.println("\n23. CHANGE PLAYER TEAM");

        ops.updateTeam(100, "MI");
        printPlayers(ops.getPlayerById(100));


        
        System.out.println("\n24. DELETE PLAYER");

        ops.deletePlayer(100);
        printPlayers(ops.getPlayerById(100));

        
        System.out.println("\n25. PLAYERS SORTED BY RUNS");
        printPlayers(ops.sortPlayersByRuns());

;
    }

    
    public static void printPlayers(List<Player> list) {

        if (list == null || list.isEmpty()) {
            System.out.println("No players found.");
            return;
        }

        for (Player p : list) {
            System.out.println(p);
        }
    }
}
