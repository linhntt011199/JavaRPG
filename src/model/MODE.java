package model;

public enum MODE {
	EASY("view/resources/modechooser/easy.png"),
	MEDIUM("view/resources/modechooser/medium.png"),
	HARD("view/resources/modechooser/hard.png");

	private String URLmode;
	
	private MODE(String URLmode) {
		this.URLmode = URLmode;
	}
	
	public String getURL() {
		return this.URLmode;
	}
	
}
