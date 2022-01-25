package chapTest;

public class Hell {
	Messa messa;
	
	public Hell(Messa messa) {
		this.messa = messa;
	}
	
	public void greet() {
		String msg = messa.getMessa();
		System.out.println(msg);
	}
	
}
