package edu.asu.gradebook.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.asu.gradebook.interfaces.PrintInterface;
import edu.asu.gradebook.model.AssignedWork;
import edu.asu.gradebook.model.Gradebook;
import edu.asu.gradebook.model.GradedWork;
import edu.asu.gradebook.model.Student;

public class HTMLOutput implements PrintInterface {

	@Override
	public boolean write(Gradebook gradeBook, String filename) {

		try {

			OutputStream htmlfile = new FileOutputStream(new File(filename));
			PrintStream printhtml = new PrintStream(htmlfile);

			String htmlheader = "<html><head>";
			htmlheader += "<title>Equivalent HTML</title>";
			htmlheader += "</head><body>";
			String htmlfooter = "</body></html>";

			htmlheader += "<table width=\"600px\" border=\"1px\" ><tr><th>Name</th><th>ID</th>";
			ArrayList<String> headers = gradeBook.getGetHeaders();
			ArrayList<Student> studentList = gradeBook.getStudentList();
			String outputHeader = "";
			for (String str : headers) {
				outputHeader += "<th>" + str + "</th>";
			}
			outputHeader += "<th>Grade</th></tr>";
			htmlheader += outputHeader;

			for (Student stu : studentList) {
				String output = "<tr>";
				output += "<td>" + stu.getsName() + "</td>";
				output += "<td>" + stu.getsID() + "</td>";

				ArrayList<AssignedWork> aWorkList = stu.getAssignedWork();

				for (AssignedWork aWork : aWorkList) {
					ArrayList<GradedWork> gradedWork = aWork.getGradedWorkList();
					for (GradedWork gw : gradedWork) {
						output += "<td>" + gw.getGradedWorkGrade() + "</td>";
					}

				}

				output += "<td>" + stu.getFinalGrade() + "</td></tr>";
				htmlheader += output;
			}

			printhtml.println(htmlheader);
			printhtml.append(htmlfooter);

			printhtml.close();
			htmlfile.close();

		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

}
