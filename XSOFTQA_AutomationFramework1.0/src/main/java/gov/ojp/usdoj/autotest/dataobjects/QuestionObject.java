/**
 * 
 */
package gov.ojp.usdoj.autotest.dataobjects;

/**
 * @author tahiraka
 *
 */

import java.util.List;

public class QuestionObject {
	
	private long id;
	//private QuestionType questionType;
	private String question;	
	private long questionID; // unique id
	
	private List<String> questionsSet;
	
	//answer
	private String answer;
	
	QuestionObject() {
		
	}

}
