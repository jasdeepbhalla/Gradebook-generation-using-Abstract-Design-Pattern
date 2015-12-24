package edu.asu.gradebook.methods;

import java.util.ArrayList;
import java.util.HashMap;

import edu.asu.gradebook.factories.AbstractFactory;
import edu.asu.gradebook.interfaces.PrintInterface;
import edu.asu.gradebook.interfaces.StudentInterface;
import edu.asu.gradebook.model.AssignedWork;
import edu.asu.gradebook.model.Gradebook;
import edu.asu.gradebook.model.GradedWork;
import edu.asu.gradebook.model.Student;

public class mainClass {
	public static void main(String[] args) {

		AbstractFactory readFactory = FactoryProducer.getFactory("LoadStudentData");
		AbstractFactory writeFactory = FactoryProducer.getFactory("PrintStudentData");

		StudentInterface studentInterfaceGraduate = readFactory.getStudentData("Graduate");
		StudentInterface studentInterfaceUnderGraduate = readFactory.getStudentData("UnderGraduate");

		Gradebook gradebookGraduate = studentInterfaceGraduate.getGradebook();
		Gradebook gradebookUnderGraduate = studentInterfaceUnderGraduate.getGradebook();

		// Calculate grades
		calculateGrades(gradebookGraduate);
		calculateGrades(gradebookUnderGraduate);

		// grad ---------------------------------------------------------------
		PrintInterface printInterfaceGradCSV = writeFactory.writeStudentData("CSV");
		printInterfaceGradCSV.write(gradebookGraduate, "GraduateCSV.csv");

		PrintInterface printInterfaceGradXML = writeFactory.writeStudentData("XML");
		printInterfaceGradXML.write(gradebookGraduate, "GraduateXML.xml");

		PrintInterface printInterfaceGradHTML = writeFactory.writeStudentData("HTML");
		printInterfaceGradHTML.write(gradebookGraduate, "GraduateHTML.html");

		// undergrad ---------------------------------------------------------------
		PrintInterface printInterfaceUnderGradCSV = writeFactory.writeStudentData("CSV");
		printInterfaceUnderGradCSV.write(gradebookUnderGraduate, "UnderGraduateCSV.csv");

		PrintInterface printInterfaceUnderGradXML = writeFactory.writeStudentData("XML");
		printInterfaceUnderGradXML.write(gradebookUnderGraduate, "UnderGraduateXML.xml");

		PrintInterface printInterfaceUnderGradHTML = writeFactory.writeStudentData("HTML");
		printInterfaceUnderGradHTML.write(gradebookUnderGraduate, "UnderGraduateHTML.html");

		System.out.println("Program execution completed successfully....");

	}

	public static void calculateGrades(Gradebook gradebook) {

		HashMap<String, Double> gSchema = gradebook.getGradingSchema();

		ArrayList<Student> studentsList = gradebook.getStudentList();

		for (Student student : studentsList) {

			ArrayList<AssignedWork> assignedWorkList = student.getAssignedWork();
			double cTotal = 0;
			for (AssignedWork assignedWork : assignedWorkList) {
				if (!gSchema.containsKey(assignedWork.getCategory()))
					return;
				double result = 0;
				int cnt = 0;
				ArrayList<GradedWork> gList = assignedWork.getGradedWorkList();
				for (GradedWork entry : gList) {
					String val = entry.getGradedWorkGrade();
					if (isNumeric(val)) {
						if (Double.parseDouble(val) > 100)
							return;
						result = result + Double.parseDouble(val);
					}
					cnt++;
				}

				double percentage = gSchema.get(assignedWork.getCategory());
				cTotal = cTotal + (result / (cnt * 100) * 100) * percentage / 100;
			}
			student.setFinalGrade(getGrade(cTotal));
		}
	}

	private static boolean isNumeric(String val) {
		try {
			double doubleVal = Double.parseDouble(val);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static String getGrade(double studentScore) {

		if (studentScore < 60) {
			return "E";
		} else if (studentScore >= 60 && studentScore < 70) {
			return "D";
		} else if (studentScore >= 70 && studentScore < 75) {
			return "C";
		} else if (studentScore >= 75 && studentScore < 80) {
			return "C+";
		} else if (studentScore >= 80 && studentScore < 84) {
			return "B-";
		} else if (studentScore >= 84 && studentScore < 87) {
			return "B";
		} else if (studentScore >= 87 && studentScore < 90) {
			return "B+";
		} else if (studentScore >= 90 && studentScore < 95) {
			return "A-";
		} else if (studentScore >= 95 && studentScore < 99) {
			return "A";
		} else if (studentScore >= 99) {
			return "A+";
		}
		return "NA";
	}
}
