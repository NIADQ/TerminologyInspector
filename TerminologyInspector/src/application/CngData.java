package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CngData {

	
	SimpleStringProperty numcount;
	SimpleStringProperty yncount;
	SimpleStringProperty datecount;
	SimpleStringProperty telcount;
	SimpleStringProperty zipcount;
	SimpleStringProperty biznocount;
	
	
	public CngData(String ncount, String ycount,
			String dcount, String tcount, String zcount, String bcount) {
		super();
		
		this.numcount = new SimpleStringProperty(ncount);
		this.yncount = new SimpleStringProperty(ycount);
		this.datecount = new SimpleStringProperty(dcount);
		this.telcount = new SimpleStringProperty(tcount);
		this.zipcount = new SimpleStringProperty(zcount);
		this.biznocount = new SimpleStringProperty(bcount);
	}


	


	public String getNumcount() {
		return numcount.get();
	}


	public void setNumcount(String ncount) {
		numcount.set(ncount);
	}


	public String getYncount() {
		return yncount.get();
	}


	public void setYncount(String ycount) {
		yncount.set(ycount);
	}


	public String getDatecount() {
		return datecount.get();
	}


	public void setDatecount(String dcount) {
		datecount.set(dcount);
	}


	public String getTelcount() {
		return telcount.get();
	}


	public void setTelcount(String tcount) {
		telcount.set(tcount);
	}


	public String getZipcount() {
		return zipcount.get();
	}


	public void setZipcount(String zcount) {
		zipcount.set(zcount);
	}
	
	public String getBiznocount() {
		return biznocount.get();
	}


	public void setBiznocount(String bcount) {
		biznocount.set(bcount);
	}
	

}
