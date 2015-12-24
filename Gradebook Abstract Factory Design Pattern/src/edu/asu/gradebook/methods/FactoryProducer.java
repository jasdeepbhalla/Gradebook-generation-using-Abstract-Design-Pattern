package edu.asu.gradebook.methods;

import edu.asu.gradebook.factories.AbstractFactory;
import edu.asu.gradebook.factories.PrintFactory;
import edu.asu.gradebook.factories.StudentFactory;

public class FactoryProducer {

	public static AbstractFactory getFactory(String opType) {

		if (opType.equals("LoadStudentData"))
			return new StudentFactory();

		if (opType.equals("PrintStudentData"))
			return new PrintFactory();

		return null;

	}

}
