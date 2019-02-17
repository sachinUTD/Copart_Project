
public class Submission {

	int subId;
	String language;
	
	
	public Submission() {
		subId = getSubId();
		language = getLanguage();
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
