import java.util.ArrayList;

public class Team {

	String teamName;
	int memberCount;
	ArrayList<Users> memberName = new ArrayList<Users>();
	
	
	public Team() {
		teamName = getTeamName();
		memberCount = getMemberCount();
		memberName = getMemberName();
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public ArrayList<Users> getMemberName() {
		return memberName;
	}

	public void setMemberName(ArrayList<Users> memberName) {
		this.memberName = memberName;
	}
	
	
	
}
