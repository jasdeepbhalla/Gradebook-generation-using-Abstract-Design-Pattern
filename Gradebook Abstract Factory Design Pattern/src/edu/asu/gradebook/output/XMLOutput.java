package edu.asu.gradebook.output;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.asu.gradebook.interfaces.PrintInterface;
import edu.asu.gradebook.model.AssignedWork;
import edu.asu.gradebook.model.Gradebook;
import edu.asu.gradebook.model.GradedWork;
import edu.asu.gradebook.model.Student;

public class XMLOutput implements PrintInterface {

	@Override
	public boolean write(Gradebook gradeBook, String outFileName) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("GradeBook");
			rootElement.setAttribute("class", gradeBook.getCourseName());
			doc.appendChild(rootElement);

			Element gradesElement = doc.createElement("Grades");
			rootElement.appendChild(gradesElement);

			ArrayList<Student> studentList = gradeBook.getStudentList();

			for (Student stu : studentList) {
				Element student = doc.createElement("Student");
				gradesElement.appendChild(student);

				Element stuName = doc.createElement("Name");
				stuName.appendChild(doc.createTextNode(stu.getsName()));
				student.appendChild(stuName);

				Element stuId = doc.createElement("ID");
				stuId.appendChild(doc.createTextNode(stu.getsID()));
				student.appendChild(stuId);

				ArrayList<AssignedWork> aWorkList = stu.getAssignedWork();
				for (AssignedWork aWork : aWorkList) {
					Element aWorkEle = doc.createElement("AssignedWork");
					aWorkEle.setAttribute("category", aWork.getCategory());
					student.appendChild(aWorkEle);

					ArrayList<GradedWork> gradedWork = aWork.getGradedWorkList();

					for (GradedWork map : gradedWork) {
						Element grWork = doc.createElement("GradedWork");
						aWorkEle.appendChild(grWork);

						Element grName = doc.createElement("Name");
						grName.appendChild(doc.createTextNode(map.getGradedWorkName()));
						grWork.appendChild(grName);

						Element grScore = doc.createElement("Grade");
						grScore.appendChild(doc.createTextNode(map.getGradedWorkGrade()));
						grWork.appendChild(grScore);

					}
				}
				Element lGrade = doc.createElement("LetterGrade");
				lGrade.appendChild(doc.createTextNode(stu.getFinalGrade()));
				student.appendChild(lGrade);

			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(outFileName));

			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		}

		return true;
	}
}
