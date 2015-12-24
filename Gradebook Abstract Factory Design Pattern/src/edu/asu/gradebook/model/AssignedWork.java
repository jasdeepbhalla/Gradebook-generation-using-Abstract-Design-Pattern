package edu.asu.gradebook.model;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignedWork {

	private String category;
	private HashMap<String, String> assignedWork;
	private ArrayList<GradedWork> gradedWorkList;

	public ArrayList<GradedWork> getGradedWorkList() {
		return gradedWorkList;
	}

	public void setGradedWorkList(ArrayList<GradedWork> gradedWorkList) {
		this.gradedWorkList = gradedWorkList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public HashMap<String, String> getAssignedWork() {
		return assignedWork;
	}

	public void setAssignedWork(HashMap<String, String> assignedWork) {
		this.assignedWork = assignedWork;
	}

}
