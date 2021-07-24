/**
 * Name: Autumn Arnold
 * Date: 4/29/2020
 * Project: Lisp Expression Evaluator
 */

package evaluator;

import java.util.*;

public class LispExpressionEvaluator {
	private String currentExpr;
	private LinkedStack<Object> tokensStack;
	private LinkedStack<Double> currentOpStack;
	
	// default constructor
	public LispExpressionEvaluator() {
		currentExpr = "";
		tokensStack = new LinkedStack<Object>();
		currentOpStack = new LinkedStack<Double>();
	}
	
	// alternate constructor
	public LispExpressionEvaluator(String inputExpression) {
		currentExpr = inputExpression;
		tokensStack = new LinkedStack<Object>();
		currentOpStack = new LinkedStack<Double>();
	}
	
	// clear the stacks
	public void reset(String inputExpression) {
		currentExpr = inputExpression;
		tokensStack.clear();
		currentOpStack.clear();
	}
	
	// evaluate current operator with its operands
	private void evaluateCurrentOperation() {
		Object currentOperand;
		double result, number;
		
		if(tokensStack.empty()) {
			throw new LispExpressionException("No input to evaluate.");
		}
		
		currentOperand = tokensStack.pop();
		
		while(currentOperand instanceof String) {
			number = Double.parseDouble((String) currentOperand);
			currentOpStack.push(number);
			
			if(tokensStack.empty()) {
				throw new LispExpressionException("Invalid input."); 
			}
			
			currentOperand = tokensStack.pop();
		}
		
		String operand = currentOperand.toString();
		switch(operand) {
			case "+":
				result = 0.0;
				if(currentOpStack.empty()) {	// situation: no operand
					throw new LispExpressionException("No operand.");
				}
				
				while(!currentOpStack.empty()) {
					result += currentOpStack.pop();
				}
				
				tokensStack.push(String.valueOf(result));
				break;
				
			case "-":
				result = 0.0;
				result = currentOpStack.pop();
				
				if(currentOpStack.empty()) {	// situation: only one number
					result *= -1;
					tokensStack.push(String.valueOf(result));
				}
				
				else {	// situation: multiple numbers
					while(!currentOpStack.empty()) {
						result -= currentOpStack.pop();
					}
					
					tokensStack.push(String.valueOf(result));
				}
				break;
				
			case "*":
				result = 1.0;
				if(currentOpStack.empty()) {	// situation: no operand
					throw new LispExpressionException("No operand.");
				}
				
				while(!currentOpStack.empty()) {
					result *= currentOpStack.pop();
				}
				
				tokensStack.push(String.valueOf(result));
				break;
				
			case "/":
				if(currentOpStack.empty()) {	// situation: no operand
					throw new LispExpressionException("No operand.");
				}
				
				result = currentOpStack.pop();
				if(currentOpStack.empty()) {	// situation: only one number
					number = 1 / result;
					result = number;
					tokensStack.push(String.valueOf(result));
				}
				
				else {	// situation: multiple numbers
					while(!currentOpStack.empty()) {
						result /= currentOpStack.pop();
					}
					
					tokensStack.push(String.valueOf(result));
				}
				break;
				
			case "(":
			case ")":
			default:
				throw new LispExpressionException();
		}
	}
	
	// evaluate current Lisp expression in currentExpr
	@SuppressWarnings("resource")
	public double evaluate() {
		Scanner currentExprScanner = new Scanner(currentExpr);
		currentExprScanner = currentExprScanner.useDelimiter("\\s*");
		
		while(currentExprScanner.hasNext()) {
			if(currentExprScanner.hasNextInt()) {
				// forces the scanner to grab all of the digits and 
				// put them on the stack
				String dataString = currentExprScanner.findInLine("\\d+");
				tokensStack.push(dataString);
			}
			else {
				// get next token, only one char in string token
				String aToken = currentExprScanner.next();
				char item = aToken.charAt(0);
				
				switch(item) {
					case '(':	// next token should be an operator
						break;
					case '+':	// push operators onto the token stack
					case '-':
					case '*':
					case '/':
						tokensStack.push(item);
						break;
					case ')':	// end of current expression, now evaluate
						evaluateCurrentOperation();
						break;
					default:	// error
						throw new LispExpressionException(item + " is not a legal expression operator.");
				}
			}
		}
		currentExprScanner.close();	
		
		return Double.parseDouble((String) tokensStack.pop());
	}
}
