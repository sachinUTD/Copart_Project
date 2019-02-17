
public class Users {

	String userName;
	String phonenum;

	
	public Users() {
		userName = getUserName();
		phonenum = getPhonenum();
	}

	public String getPhonenum() {
		return phonenum;
	}



	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
