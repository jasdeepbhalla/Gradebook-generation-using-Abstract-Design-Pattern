package edu.asu.gradebook.factories;

import edu.asu.gradebook.concreteclasses.Graduate;
import edu.asu.gradebook.concreteclasses.UnderGraduate;
import edu.asu.gradebook.interfaces.PrintInterface;
import edu.asu.gradebook.interfaces.StudentInterface;

public class StudentFactory extends AbstractFactory {
	@Override
	public StudentInterface getStudentData(String studentType) {
		if (studentType.equalsIgnoreCase("Graduate"))
			return new Graduate();

		else if (studentType.equalsIgnoreCase("UnderGraduate"))
			return new UnderGraduate();
		return null;

	}

	@Override
	public PrintInterface writeStudentData(String outputType) {
		// TODO Auto-generated method stub
		return null;
	}

}
