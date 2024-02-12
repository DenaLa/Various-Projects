package atm;

public class Account {
	public String name;
	private int id;
	private float checkings;
	private float savings;
	
	Account(String name, int ID){
		this.name = name;
		this.id = ID;
		checkings = 0;
		savings = 0;
	}
	
	Account(String name, int ID, float c, float s){
		this.name = name;
		this.id = ID;
		checkings = c;
		savings = s;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return id;
	}
	
	public float getCheckings() {
		return checkings;
	}
	
	public void setCheckings(float a) {
		checkings = a;
	}
	
	public void modifyCheckings(float a) {
		checkings = (Math.round((checkings + a) * 100.0f)/100.0f);;
	}
	
	public float getSavings() {
		return savings;
	}
	
	public void setSavings(float a) {
		savings = a;
	}
	
	public void modifySavings(float a) {
		savings = (Math.round((savings + a) * 100.0f)/100.0f);
	}
	
	
	
}
