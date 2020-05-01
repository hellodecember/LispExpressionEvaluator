/*
 * Name: Autumn Arnold
 * Date: 4/29/2020
 * Project: Adding a GUI to an old SF State Project
 */
package lispexpressionevaluator;

/**
 *
 * @author Autumn
 */
public class LispExpressionException extends RuntimeException {
    public LispExpressionException()
    {
	this("");
    }

    public LispExpressionException(String errorMsg) 
    {
	super(errorMsg);
    }
}
