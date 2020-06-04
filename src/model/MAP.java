package model;

public enum MAP {
	FEMALE("view/resources/characterchooser/femalePerson.png"),
	MALE("view/resources/characterchooser/malePerson.png"),
	ROBOT("view/resources/characterchooser/robot.png");
	
	private String urlCharacter;
	
	private MAP(String urlCharacter) {
		this.urlCharacter = urlCharacter;
	}
	public String getUrl() {
		return this.urlCharacter;
	}
}
