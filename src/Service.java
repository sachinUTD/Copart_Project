import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/*
 *	\\Sachin Arya Submission//
 * teams containing all the team information it includes the User object too with the constraint of max 3 user in a team.
 * userMap is storing the all registered User information and User phone number is kind of primary key for user data 
 * teamsub is the information of all the submission done in the Hackathon 
 * sublist is hashMap and contains the information of all the submission with key as a teamName. each teamName having a Arraylist of Submission and max can have 3 submission with 
  	max 2 same language submission 
 
 ***not making any constraint or making any case for phone number length and other specific details. tried to be specific for the warning messages 
 * 
 * Method //
 * individual_team > for Individual registration
 * grp_team > for the Team registration
 * team_sub > for the team submission
 * team_remove > for any de-registration
 * 
 * */


public class Service {
	static String teamType;
	static boolean flag_team;
	static int count = 1;
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Team> teams = new ArrayList<>();
	static HashMap<String, Users> userMap = new HashMap<String, Users>();
	static ArrayList<Submission> teamsub = new ArrayList<Submission>();
	static HashMap<String, ArrayList<Submission>> sublist = new HashMap<String,ArrayList<Submission>>();
	public static void main(String[] args) {
		System.out.println("*********Welcome to HackaThon***********");
		do {
		System.out.printf("Press 'I' for Individual entry"
				+ "\nPress 'T' for team entry"
				+ "\nPress 'D' for Deregister"
				+ "\npress 'S' for Subission "
				+ "\nPress 'E' for Exit");
		teamType = sc.next();
		if(teamType.equalsIgnoreCase("I"))
		{
			individual_team();
		}
		
		if(teamType.equalsIgnoreCase("T"))
		{
			grp_team();
		}
		
		if(teamType.equalsIgnoreCase("S"))
		{
			team_sub();
		}
		
		if(teamType.equalsIgnoreCase("D"))
		{
			team_remove();
		}
		
		}while(!teamType.equalsIgnoreCase("E"));
	}
	
	// Remove individual from team
	
	private static void team_remove() {
		if(teams.size()!=0)
		{
		System.out.println("Member Phone Number:");
		String PhnNum= sc.next();
		if(userMap.containsKey(PhnNum))
		{
			for (int i = 0; i < teams.size(); i++) {
				for (int j = 0; j < teams.get(i).memberName.size(); j++) {
					if(teams.get(i).memberName.get(j).phonenum.equalsIgnoreCase(PhnNum))
					{
						if(teams.get(i).memberCount==1)
						{
							teams.remove(i);
							userMap.remove(PhnNum);
						}
						else
						{
							teams.get(i).memberName.remove(j);
							teams.get(i).memberCount--;
						}
					}
				}
			}
			String p = teams.get(0).memberName.get(0).phonenum;
			System.out.println(p);
			userMap.remove(PhnNum);
		}
		else
		{
			System.out.println(">>>>>>>>>>>>>User does not exist");
		}
		}
		else
		{
			System.out.println(">>>>>>>>>>There is no team Register Yet");
		}
	}


	// Submission code
	
	private static void team_sub() {
	if(teams.size()!=0)
	{
	Submission sub = new Submission();
	boolean flag = false;
	String teamname;
	System.out.println("Team Name: ");
	do {
		 teamname = sc.next();
		for (int j = 0; j < teams.size(); j++) {
			if(!teams.get(j).teamName.equalsIgnoreCase(teamname))
				{
					System.out.println(">>>>>>>>>>>enter correct team name");
					flag = true;
				}
			else
			{
				sublist.put(teamname,teamsub);
				flag=false;
			}
		}
	}while(flag==true);
	if(sublist.get(teamname).size()<3)
	{	
	System.out.println("Language");
	do {	
	String language= sc.next();
	if(sublist.size()!=0)
	{
	if(sublist.get(teamname).size()>=2)
	{
	if(sublist.get(teamname).get(0).language.equalsIgnoreCase(language)&& sublist.get(teamname).get(1).language.equalsIgnoreCase(language)&&sublist.get(teamname).size()==2)
	{
		System.out.println(">>>>>>>>>>>Exceed Language submission try another Language");
		flag = true;
	}
	else
	{
		sub.setLanguage(language);
		flag=false;
	}
	}
	else
	{
		sub.setLanguage(language);
		flag=false;
	}
	}	
	else
	{
		sub.setLanguage(language);
		flag=false;
	}
	}while(flag==true);
	System.out.println("submission ID");
	sub.setSubId(count);
	System.out.println(count);
	count++;
	
	teamsub.add(sub);
	sublist.put(teamname,teamsub);
	}
	else
	{
		System.out.println(">>>>>>>>>>>>Exceed Submission Limit Thank you!");
	}
	}
	else
	{
		System.out.println(">>>>>>>>>>There is no team Register Yet");
	}
	}


	// grp Team entry
	
	private static void grp_team() {
		boolean duplicate_teamName;
		Team grpTeam = new Team();
		System.out.println("unique team Name:");
		do {
		grpTeam.setTeamName(sc.next());
		duplicate_teamName = findTname(grpTeam.getTeamName(),teams);
		if(duplicate_teamName==true)
			System.out.println(">>>>>>>>>>>>>preoccupied Team Name Please re-enter");
		}while(duplicate_teamName!=false);
		System.out.println("Member Count");
		do {
			grpTeam.setMemberCount(sc.nextInt());
			if(grpTeam.getMemberCount()>3 || grpTeam.getMemberCount()<0)
				System.out.println(">>>>>>>>>>>>Please enter valid count between 0 and 3");
		}while(grpTeam.getMemberCount()>3 || grpTeam.getMemberCount()<0);
		//System.out.println("Member_Name:");
		for (int i = 0; i < grpTeam.getMemberCount(); i++) {
			if(userMap== null)
			{
			Users user = new Users();
			System.out.println((i+1)+" Phone Number:");
			user.setPhonenum(sc.next());
			System.out.println((i+1)+" Member Name:");
			user.setUserName(sc.next());
			grpTeam.memberName.add(user);
			userMap.put(user.getPhonenum(), user);
			}
			else
			{
				boolean flag = false;
				do {
				System.out.println((i+1)+" Phone Number:");
				String phonenum = sc.next(); 
				if(userMap.containsKey(phonenum))
				{
					for (int j = 0; j < grpTeam.memberName.size(); j++) {
						if(grpTeam.memberName.get(j).phonenum.equalsIgnoreCase(phonenum))
						{
							System.out.println("Not");
							flag = true;
						}
					}
					for (int j = 0; j < teams.size(); j++) {
						for (int j2 = 0; j2 < teams.get(j).memberName.size(); j2++) {
							if(teams.get(j).memberCount>1 && teams.get(j).memberName.get(j2).phonenum.equalsIgnoreCase(phonenum))
							{
								System.out.println(">>>>>>>>>>>>>User Already Exist");
								flag = true;
							}
						}
					}
					if(flag==false)
					grpTeam.memberName.add(userMap.get(phonenum));
					
				}
				else
				{
					flag = false;
					Users user = new Users();
					user.setPhonenum(phonenum);
					System.out.println((i+1)+" Member Name:");
					user.setUserName(sc.next());
					grpTeam.memberName.add(user);
					userMap.put(user.getPhonenum(), user);
				}
				}while(flag==true);
			}
		}teams.add(grpTeam);
	}

	// individual team player entry....
	
	private static void individual_team() {
		flag_team = false;
		boolean duplicate_teamName;
		Team indObj = new Team();	
		System.out.println("unique team Name:");
		do {
		indObj.setTeamName(sc.next());
		duplicate_teamName = findTname(indObj.getTeamName(),teams);
		if(duplicate_teamName==true)
			System.out.println(">>>>>>>>>>>preoccupied Team Name Please re-enter");
		}while(duplicate_teamName!=false);
		indObj.setMemberCount(1);
		for (int i = 0; i < indObj.getMemberCount(); i++) {
			if(userMap.size()== 0)
			{
			Users user = new Users();
			System.out.println((i+1)+" Phone Number:");
			user.setPhonenum(sc.next());
			System.out.println((i+1)+" Member Name:");
			user.setUserName(sc.next());
			indObj.memberName.add(user);
			userMap.put(user.getPhonenum(), user);
			}
			else
			{
				System.out.println((i+1)+" Phone Number:");
				String phonenum = sc.next(); 
				if(userMap.containsKey(phonenum))
				{
					indObj.memberName.add(userMap.get(phonenum));
					System.out.println(">>>>>>>>>>>>>User Already Exist");
					flag_team = true;
				}
				else
				{
					Users user = new Users();
					user.setPhonenum(phonenum);
					System.out.println((i+1)+" Member Name:");
					user.setUserName(sc.next());
					indObj.memberName.add(user);
					userMap.put(user.getPhonenum(), user);
				}
			}
		}
		if(flag_team==false)
		teams.add(indObj);
	}

	
	// for finding duplicate team name	
	private static boolean findTname(String name, ArrayList<Team> ind2) {
		for (int i = 0; i < ind2.size(); i++) {
			if(name.equalsIgnoreCase(ind2.get(i).teamName))
					{
						return true;
					}
		}
		return false;
	}
}
