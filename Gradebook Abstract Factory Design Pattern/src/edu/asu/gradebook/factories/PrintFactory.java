package edu.asu.gradebook.factories;

import edu.asu.gradebook.interfaces.PrintInterface;
import edu.asu.gradebook.interfaces.StudentInterface;
import edu.asu.gradebook.output.CSVOutput;
import edu.asu.gradebook.output.HTMLOutput;
import edu.asu.gradebook.output.XMLOutput;

public class PrintFactory extends AbstractFactory {

	@Override
	public StudentInterface getStudentData(String studentType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintInterface writeStudentData(String outputType) {
		if (outputType.equalsIgnoreCase("CSV"))
			return new CSVOutput();

		if (outputType.equalsIgnoreCase("HTML"))
			return new HTMLOutput();

		if (outputType.equalsIgnoreCase("XML"))
			return new XMLOutput();

		return null;
	}

}
