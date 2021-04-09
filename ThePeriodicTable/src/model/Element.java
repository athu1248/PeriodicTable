package model;

public class Element {
	
	private String name; 
	private String symbol; 
	private int atmNo;
	private int massNo;
	private String eConfig; 
	private String fact;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
	public int getAtmNo() {
		return atmNo;
	}
	public void setAtmNo(int atmNo) {
		this.atmNo = atmNo;
	}
	
	
	public int getMassNo() {
		return massNo;
	}
	public void setMassNo(int massNo) {
		this.massNo = massNo;
	}
	
	
	public String geteConfig() {
		return eConfig;
	}
	public void seteConfig(String eConfig) {
		this.eConfig = eConfig;
	}
	
	
	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}

}
