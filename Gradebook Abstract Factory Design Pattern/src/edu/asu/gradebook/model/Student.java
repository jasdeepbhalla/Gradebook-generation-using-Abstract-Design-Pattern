package edu.asu.gradebook.model;

import java.util.ArrayList;

public class Student {

	private String sName;
	private String sID;
	private ArrayList<AssignedWork> assignedWork;
	private String finalGrade;

	public String getsName() {
		return sName;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}

	public ArrayList<AssignedWork> getAssignedWork() {
		return assignedWork;
	}

	public void setAssignedWork(ArrayList<AssignedWork> assignedWork) {
		this.assignedWork = assignedWork;
	}

}
