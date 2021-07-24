/**
 * Name: Autumn Arnold
 * Date: 4/29/2020
 * Project: Lisp Expression Evaluator
 */

package evaluator;


@SuppressWarnings("serial")
public class LispExpressionException extends RuntimeException {
	// default constructor 
	public LispExpressionException() {
		this("");
	}
	
	// alternate constructor
	public LispExpressionException(String errorMsg) {
		super(errorMsg);
	}
}
