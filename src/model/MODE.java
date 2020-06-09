package model;

public enum MODE {
		EASY("view/resources/modechooser/easy.png"),
		MEDIUM("view/resources/modechooser/medium.png"),
		HARD("view/resources/modechooser/hard.png");
		
		private String urlMode;
		
		private MODE(String urlMode) {
			this.urlMode = urlMode;
		}
		
		//method return url
		public String getUrl() {
			return this.urlMode;
		}
}