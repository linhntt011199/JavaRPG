package model;

public enum MODE {
<<<<<<< HEAD
		
		EASY("view/resource/modechooser/easy.png"),
		MEDIUM("view/resource/modechooser/medium.png"),
		HARD("view/resource/modechooser/hard.png");
		
		private String urlMode;
		
		private MODE(String urlMode) {
			this.urlMode = urlMode;
		}
		
		//method return url
		public String getUrl() {
			return this.urlMode;
		}
}

=======
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
>>>>>>> refs/heads/GameView
