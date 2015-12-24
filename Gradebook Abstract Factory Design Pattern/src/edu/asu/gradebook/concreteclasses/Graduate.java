package edu.asu.gradebook.concreteclasses;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.gradebook.interfaces.StudentInterface;
import edu.asu.gradebook.model.AssignedWork;
import edu.asu.gradebook.model.Gradebook;
import edu.asu.gradebook.model.GradedWork;
import edu.asu.gradebook.model.Student;

public class Graduate implements StudentInterface {

	Gradebook gradebook = new Gradebook();

	@Override
	public Gradebook getGradebook() {
		String file = "Grades.xml";

		HashMap<String, Double> gSchema = new HashMap<>();
		ArrayList<Student> studArrayList = new ArrayList<>();

		ArrayList<String> headerList = new ArrayList<>();
		try {
			File inputFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList gradeBookNodeList = doc.getElementsByTagName("GradeBook");
			Element gbElement = (Element) gradeBookNodeList.item(0);
			gradebook.setCourseName(gbElement.getAttribute("class"));
			NodeList gSchemaNode = doc.getElementsByTagName("GradingSchema");
			NodeList gSchemaList = gSchemaNode.item(0).getChildNodes();
			for (int i = 0; i < gSchemaList.getLength(); i++) {
				Node node = gSchemaList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String category = eElement.getElementsByTagName("Category").item(0).getTextContent();
					String percentage = eElement.getElementsByTagName("Percentage").item(0).getTextContent();
					gSchema.put(category, Double.parseDouble(percentage));

				}
			}
			NodeList gradeNode = doc.getElementsByTagName("Grades");
			NodeList studentList = doc.getElementsByTagName("Student");
			for (int i = 0; i < studentList.getLength(); i++) {
				Student stu = new Student();
				Node node = studentList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
					String id = eElement.getElementsByTagName("ID").item(0).getTextContent();
					stu.setsName(name);
					stu.setsID(id);

					NodeList assignedWork = eElement.getElementsByTagName("AssignedWork");
					ArrayList<AssignedWork> assignedWorkList = new ArrayList<>();
					for (int j = 0; j < assignedWork.getLength(); j++) {
						AssignedWork aWork = new AssignedWork();
						Node snode = assignedWork.item(j);
						if (snode.getNodeType() == Node.ELEMENT_NODE) {
							Element sElement = (Element) snode;
							aWork.setCategory(sElement.getAttribute("category"));
							NodeList gradedWorkList = sElement.getElementsByTagName("GradedWork");
							ArrayList<GradedWork> gradedWorkArrayList = new ArrayList<>();

							for (int k = 0; k < gradedWorkList.getLength(); k++) {
								Node gnode = gradedWorkList.item(k);
								if (gnode.getNodeType() == Node.ELEMENT_NODE) {
									Element gElement = (Element) gnode;

									GradedWork gWork = new GradedWork();
									gWork.setGradedWorkGrade(gElement.getElementsByTagName("Grade").item(0)
											.getTextContent());
									gWork.setGradedWorkName(gElement.getElementsByTagName("Name").item(0)
											.getTextContent());

									if (!headerList.contains(gElement.getElementsByTagName("Name").item(0)
											.getTextContent()))
										headerList.add(gElement.getElementsByTagName("Name").item(0).getTextContent());

									gradedWorkArrayList.add(gWork);
								}
							}
							aWork.setGradedWorkList(gradedWorkArrayList);
							assignedWorkList.add(aWork);
						}

					}
					stu.setAssignedWork(assignedWorkList);
					studArrayList.add(stu);
				}
			}

			gradebook.setGradingSchema(gSchema);
			gradebook.setStudentList(studArrayList);
			gradebook.setGetHeaders(headerList);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return gradebook;
	}
}
