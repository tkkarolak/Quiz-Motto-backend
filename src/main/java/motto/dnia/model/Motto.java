package motto.dnia.model;

public class Motto {

	private String mottoText;
	private String mottoAuthor;
	
	public String getMottoText() {
		return mottoText;
	}
	public void setMottoText(String mottoText) {
		this.mottoText = mottoText;
	}
	
	public String getMottoAuthor() {
		return mottoAuthor;
	}
	public void setMottoAuthor(String mottoAuthor) {
		this.mottoAuthor = mottoAuthor;
	}
	
	@Override
	public String toString() {
		return mottoText+" {"+mottoAuthor+"}";
	}

}
