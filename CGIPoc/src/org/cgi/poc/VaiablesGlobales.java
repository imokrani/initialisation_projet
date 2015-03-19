package org.cgi.poc;

import android.app.Application;

public class VaiablesGlobales extends Application {

	private String UUUIDStringBoard;
	private int countPhotosBoard; 

	public String getUUUIDStringBoard() {
		return UUUIDStringBoard;
	}
	
	public Data data; 
	private Notes note;  

	public void setUUUIDStringBoard(String uUUIDStringBoard) {
		UUUIDStringBoard = uUUIDStringBoard;
	}

	public int getCountPhotosBoard() {
		return countPhotosBoard;
	}

	public void setCountPhotosBoard(int countPhotosBoard) {
		this.countPhotosBoard = countPhotosBoard;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Notes getNote() {
		return note;
	}

	public void setNote(Notes note) {
		this.note = note;
	} 
	
}
