package edu.asu.gradebook.interfaces;

import edu.asu.gradebook.model.Gradebook;

public interface PrintInterface {
	public boolean write(Gradebook gradebook, String fileName);
}
