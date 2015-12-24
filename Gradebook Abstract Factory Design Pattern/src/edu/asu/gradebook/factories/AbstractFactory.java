package edu.asu.gradebook.factories;

import edu.asu.gradebook.interfaces.PrintInterface;
import edu.asu.gradebook.interfaces.StudentInterface;

public abstract class AbstractFactory {

	public abstract StudentInterface getStudentData(String studentType);

	public abstract PrintInterface writeStudentData(String outputType);
}
