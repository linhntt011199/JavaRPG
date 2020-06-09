package model;

public enum MODE {
		
		EASY("view/resource/shipchooser/easy.png"),
		MEDIUM("view/resource/shipchooser/medium.png"),
		HARD("view/resource/shipchooser/hard.png");
		
		private String urlMode;
		
		private MODE(String urlMode) {
			this.urlMode = urlMode;
		}
		
		//method return url
		public String getUrl() {
			return this.urlMode;
		}
}

