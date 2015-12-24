package edu.asu.gradebook.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.simpleframework.xml.Root;

@Root
public class Gradebook {

	private String courseName;

	private HashMap<String, Double> gradingSchema;

	private ArrayList<Student> studentList;
	private ArrayList<String> getHeaders;

	public ArrayList<String> getGetHeaders() {
		return getHeaders;
	}

	public void setGetHeaders(ArrayList<String> getHeaders) {
		this.getHeaders = getHeaders;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseID) {
		this.courseName = courseID;
	}

	public HashMap<String, Double> getGradingSchema() {
		return gradingSchema;
	}

	public void setGradingSchema(HashMap<String, Double> gradingSchema) {
		this.gradingSchema = gradingSchema;
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

}
