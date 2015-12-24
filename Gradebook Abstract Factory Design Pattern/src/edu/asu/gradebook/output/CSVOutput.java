package edu.asu.gradebook.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.asu.gradebook.interfaces.PrintInterface;
import edu.asu.gradebook.model.AssignedWork;
import edu.asu.gradebook.model.Gradebook;
import edu.asu.gradebook.model.GradedWork;
import edu.asu.gradebook.model.Student;

public class CSVOutput implements PrintInterface {

	@Override
	public boolean write(Gradebook gradebook, String outFilename) {
		try {

			FileWriter fileWriter = new FileWriter(new File(outFilename));

			ArrayList<Student> studentsList = gradebook.getStudentList();
			ArrayList<String> headers = gradebook.getGetHeaders();

			fileWriter.append("Name");
			fileWriter.append(',');
			fileWriter.append("ID");
			for (String str : headers) {
				fileWriter.append(',');
				fileWriter.append(str);
			}
			fileWriter.append(',');
			fileWriter.append("Grade");
			fileWriter.append("\n");

			for (Student student : studentsList) {
				String outputString = "";
				outputString += student.getsName() + ",";
				outputString += student.getsID() + ",";

				ArrayList<AssignedWork> assignedWorkList = student.getAssignedWork();

				for (AssignedWork assignedWork : assignedWorkList) {
					ArrayList<GradedWork> gradedWork = assignedWork.getGradedWorkList();

					for (GradedWork gdWork : gradedWork) {
						outputString += gdWork.getGradedWorkGrade() + ",";
					}
				}
				outputString += student.getFinalGrade();

				fileWriter.append(outputString);
				fileWriter.append("\n");

			}

			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
