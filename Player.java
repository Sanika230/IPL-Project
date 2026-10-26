package com.tka23;

public class Player {
	
	private  int Playerid;
	  private String Playername;
	  private String TeamName;
	  private int Playerruns;
	  private int Playercatches;
	  private int Playerwickets;
	 
	  
	  public Player(int playerid, String playername, String teamName, int playerruns, int playercatches,
			int playerwickets,String Category) {
		
		Playerid = playerid;
		Playername = playername;
		TeamName = teamName;
		Playerruns = playerruns;
		Playercatches = playercatches;
		Playerwickets = playerwickets;
	  }
	  @Override
	public String toString() {
		return "Player [Playerid=" + Playerid + ", Playername=" + Playername + ", TeamName=" + TeamName
				+ ", Playerruns=" + Playerruns + ", Playercatches=" + Playercatches + ", Playerwickets=" + Playerwickets
				+ ", getPlayerid()=" + getPlayerid() + ", getPlayername()=" + getPlayername() + ", getTeamName()="
				+ getTeamName() + ", getPlayerruns()=" + getPlayerruns() + ", getPlayercatches()=" + getPlayercatches()
				+ ", getPlayerwickets()=" + getPlayerwickets() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	  public int getPlayerid() {
		return Playerid;
	}
	  public void setPlayerid(int playerid) {
		  Playerid = playerid;
	  }
	  public String getPlayername() {
		  return Playername;
	  }
	  public void setPlayername(String playername) {
		  Playername = playername;
	  }
	  public String getTeamName() {
		  return TeamName;
	  }
	  public void setTeamName(String teamName) {
		  TeamName = teamName;
	  }
	  public int getPlayerruns() {
		  return Playerruns;
	  }
	  public void setPlayerruns(int playerruns) {
		  Playerruns = playerruns;
	  }
	  public int getPlayercatches() {
		  return Playercatches;
	  }
	  public void setPlayercatches(int playercatches) {
		  Playercatches = playercatches;
	  }
	  public int getPlayerwickets() {
		  return Playerwickets;
	  }
	  public void setPlayerwickets(int playerwickets) {
		  Playerwickets = playerwickets;
	  }
	  public Player() {
		super();
	  }
	  public String getCategory() {
		
		return null;
	  }
	  
	  
	

}
