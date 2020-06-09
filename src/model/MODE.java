package model;

public enum MODE {
		
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

