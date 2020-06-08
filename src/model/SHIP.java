package model;

public enum SHIP {
		
		EASY("view/resource/shipchooser/easy.png"),
		MEDIUM("view/resource/shipchooser/medium.png"),
		HARD("view/resource/shipchooser/hard.png");
		
		private String urlShip;
		
		private SHIP(String urlShip) {
			this.urlShip = urlShip;
		}
		
		//method return url
		public String getUrl() {
			return this.urlShip;
		}
}

