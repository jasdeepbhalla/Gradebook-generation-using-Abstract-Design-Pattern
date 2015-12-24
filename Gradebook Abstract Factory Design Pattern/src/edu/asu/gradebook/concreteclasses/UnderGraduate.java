package edu.asu.gradebook.concreteclasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.asu.gradebook.interfaces.StudentInterface;
import edu.asu.gradebook.model.AssignedWork;
import edu.asu.gradebook.model.Gradebook;
import edu.asu.gradebook.model.GradedWork;
import edu.asu.gradebook.model.Student;

public class UnderGraduate implements StudentInterface {

	private Gradebook gradebook = new Gradebook();

	@Override
	public Gradebook getGradebook() {

		String inputFile = "Grades.json";
		HashMap<String, Double> gSchema = new HashMap<>();
		ArrayList<Student> studArrayList = new ArrayList<>();
		ArrayList<String> headerList = new ArrayList<>();
		try {
			File initialInputFile = new File(inputFile);
			InputStream stream = new FileInputStream(initialInputFile);
			InputStreamReader targetStream = new InputStreamReader(stream);

			BufferedReader reader = new BufferedReader(targetStream);
			String requestData = "";
			String temp = reader.readLine();
			while (temp != null) {
				requestData = requestData + temp.trim();
				temp = reader.readLine();
			}
			reader.close();
			JsonParser jsonParser = new JsonParser();
			if (requestData != null && !requestData.isEmpty()) {
				JsonObject jsonObj = jsonParser.parse(requestData).getAsJsonObject();
				gradebook.setCourseName(jsonObj.getAsJsonObject("GradeBook").get("class").getAsString());

				JsonObject gradeSchObj = jsonObj.getAsJsonObject("GradeBook").getAsJsonObject("GradingSchema");

				JsonArray scArray = gradeSchObj.getAsJsonArray("GradeItem");

				for (int i = 0; i < scArray.size(); i++) {
					JsonObject eObj = scArray.get(i).getAsJsonObject();
					gSchema.put(eObj.get("Category").getAsString(),
							Double.parseDouble(eObj.get("Percentage").getAsString()));

				}

				JsonArray studentArray = jsonObj.getAsJsonObject("GradeBook").getAsJsonObject("Grades")
						.getAsJsonArray("Student");

				for (JsonElement element : studentArray) {
					Student stu = new Student();
					stu.setsName(element.getAsJsonObject().get("Name").getAsString());
					stu.setsID(element.getAsJsonObject().get("ID").getAsString());

					JsonArray asWorkArray = element.getAsJsonObject().getAsJsonArray("AssignedWork");
					ArrayList<AssignedWork> workList = new ArrayList<>();
					for (JsonElement aWork : asWorkArray) {
						AssignedWork assignedWork = new AssignedWork();
						assignedWork.setCategory(aWork.getAsJsonObject().get("category").getAsString());

						HashMap<String, String> assignedMap = new HashMap<>();
						ArrayList<GradedWork> gradedWorkArrayList = new ArrayList<>();
						JsonArray gradedWorkArray = aWork.getAsJsonObject().getAsJsonArray("GradedWork");

						for (JsonElement gWork : gradedWorkArray) {
							GradedWork gradedWork = new GradedWork();
							gradedWork.setGradedWorkGrade(gWork.getAsJsonObject().get("Grade").getAsString());
							gradedWork.setGradedWorkName(gWork.getAsJsonObject().get("Name").getAsString());
							gradedWorkArrayList.add(gradedWork);
							if (!headerList.contains(gWork.getAsJsonObject().get("Name").getAsString()))
								headerList.add(gWork.getAsJsonObject().get("Name").getAsString());
						}
						assignedWork.setAssignedWork(assignedMap);
						assignedWork.setGradedWorkList(gradedWorkArrayList);
						workList.add(assignedWork);
					}
					stu.setAssignedWork(workList);
					studArrayList.add(stu);
				}
			}

			gradebook.setGradingSchema(gSchema);
			gradebook.setStudentList(studArrayList);
			gradebook.setGetHeaders(headerList);

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return gradebook;
	}

}
