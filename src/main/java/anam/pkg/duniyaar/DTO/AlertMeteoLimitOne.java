package anam.pkg.duniyaar.DTO;

public class AlertMeteoLimitOne {
	 private Long id;
		private String titre;
		
		public AlertMeteoLimitOne() {
			super();
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public AlertMeteoLimitOne(Long id, String titre) {
			super();
			this.id = id;
			this.titre = titre;
		}
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}
}
