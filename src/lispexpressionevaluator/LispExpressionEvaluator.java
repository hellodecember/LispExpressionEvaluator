/*
 * Name: Autumn Arnold
 * Date: 4/29/2020
 * Project: Adding a GUI to an old SF State Project
 */
package lispexpressionevaluator;

import java.util.*;

/**
 *
 * @author Autumn
 */
public class LispExpressionEvaluator {
    // Current input Lisp expression
    private String currentExpr;

    // Main expression stack, see algorithm in evaluate()
    private LinkedStack<Object> tokensStack;
    private LinkedStack<Double> currentOpStack;
    
    // default constructor
    // set currentExpr to "" 
    // create LinkedStack objects
    public LispExpressionEvaluator()
    {
	// add statements
      currentExpr = "";
      tokensStack = new LinkedStack<Object>();
      currentOpStack = new LinkedStack<Double>();
    }

    // constructor with an input expression 
    // set currentExpr to inputExpression 
    // create LinkedStack objects
    public LispExpressionEvaluator(String inputExpression) 
    {
	// add statements
      currentExpr = inputExpression;
      tokensStack = new LinkedStack<Object>();
      currentOpStack = new LinkedStack<Double>();
      
    }

    // set currentExpr to inputExpression 
    // clear stack objects
    public void reset(String inputExpression) 
    {
	// add statements
      currentExpr = inputExpression;
      tokensStack.clear();
      currentOpStack.clear();
    }
    
    // This function evaluates current operator with its operands
    // See complete algorithm in evaluate()
    //
    // Main Steps:
    // 		Pop operands from tokensStack and push them onto 
    // 			currentOpStack until you find an operator
    //  	Apply the operator to the operands on currentOpStack
    //          Push the result into tokensStack
    //
    private void evaluateCurrentOperation()
    {
	// add statements
      Object currentOperand;
      double result, number;
      
      if(tokensStack.empty()){
        throw new LispExpressionException("No input to evaluate.");
      }
      
      currentOperand = tokensStack.pop();
      
      while(currentOperand instanceof String) {
        number = Double.parseDouble((String) currentOperand);
        currentOpStack.push(number);
        
        if(tokensStack.empty()){
          throw new LispExpressionException("Invalid input.");
        }
        
        currentOperand = tokensStack.pop();
      }    
      
      String operand = currentOperand.toString();
      
      switch(operand) {
        case "+":
          result = 0.0;
          
          if(currentOpStack.empty()) {  //situation: no operand
            throw new LispExpressionException("No operand.");
          }
          
          while(!currentOpStack.empty()){
            result += currentOpStack.pop();
          }
          tokensStack.push(String.valueOf(result));
          break;
          
        case "-":
          result = 0.0;
          result = currentOpStack.pop();
          
          if(currentOpStack.empty()){  //situation: only one number
            result *= -1;
            tokensStack.push(String.valueOf(result));
          }
          
          else {     //situation: multiple numbers
            while(!currentOpStack.empty()){
              result -= currentOpStack.pop();
            }
            tokensStack.push(String.valueOf(result));
          }
          break;
          
        case "*":
          result = 1.0;
          
          if(currentOpStack.empty()) {  //situation: no operand
            throw new LispExpressionException("No operand.");
          }
          
          while(!currentOpStack.empty()){
            result *= currentOpStack.pop();
          }
          tokensStack.push(String.valueOf(result));
          break;
          
        case "/":
          if(currentOpStack.empty()) {  //situation: no operands
            throw new LispExpressionException("No operands.");
          }
          
          result = currentOpStack.pop();
          
          if(currentOpStack.empty()){
            number = 1 / result;
            result = number;
            tokensStack.push(String.valueOf(result));
          }
          
          else {  //situation: multiple numbers
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
    
    /**
     * This funtion evaluates current Lisp expression in currentExpr
     * It return result of the expression 
     *
     * The algorithm:  
     *
     * Step 1   Scan the tokens in the string.
     * Step 2		If you see an operand, push operand object onto the tokensStack
     * Step 3  	    	If you see "(", next token should be an operator
     * Step 4  		If you see an operator, push operator object onto the tokensStack
     * Step 5		If you see ")"  // steps in evaluateCurrentOperation() :
     * Step 6			Pop operands and push them onto currentOpStack 
     * 					until you find an operator
     * Step 7			Apply the operator to the operands on currentOpStack
     * Step 8			Push the result into tokensStack
     * Step 9    If you run out of tokens, the value on the top of tokensStack is
     *           is the result of the expression.
     */
    public double evaluate()
    {
        // only outline is given...
        // you need to add statements/local variables
        // you may delete or modify any statements in this method

        // use scanner to tokenize currentExpr
        Scanner currentExprScanner = new Scanner(currentExpr);
        
        // Use zero or more white space as delimiter,
        // which breaks the string into single character tokens
        currentExprScanner = currentExprScanner.useDelimiter("\\s*");

        // Step 1: Scan the tokens in the string.
        while (currentExprScanner.hasNext())
        {
		
     	    // Step 2: If you see an operand, push operand object onto the tokensStack
            if (currentExprScanner.hasNextInt())
            {
                // This force scanner to grab all of the digits
                // Otherwise, it will just get one char
                String dataString = currentExprScanner.findInLine("\\d+");

   		// more ...
                tokensStack.push(dataString);
            }
            else
            {
                // Get next token, only one char in string token
                String aToken = currentExprScanner.next();
                //System.out.println("Other: " + aToken);
                char item = aToken.charAt(0);
                
                switch (item)
                {
     		    // Step 3: If you see "(", next token shoube an operator
                    case '(':
                      break;
                      
     		    // Step 4: If you see an operator, push operator object onto the tokensStack
                    case '+':
                      tokensStack.push(item);
                      break;
                    case '-':
                      tokensStack.push(item);
                      break;
                    case '*':
                      tokensStack.push(item);
                      break;
                    case '/':
                      tokensStack.push(item);
                      break;
                      
     		    // Step 5: If you see ")"  // steps in evaluateCurrentOperation() :
                    case ')':
                      evaluateCurrentOperation();
                      break;
                      
                    default:  // error
                        throw new LispExpressionException(item + " is not a legal expression operator");
                } // end switch
            } // end else
        } // end while
        
        // Step 9: If you run out of tokens, the value on the top of tokensStack is
        //         is the result of the expression.
        //
        //         return result
       
	return Double.parseDouble((String) tokensStack.pop()); // return result
    }
    
    //=====================================================================
    // DO NOT MODIFY ANY STATEMENTS BELOW
    // Quick test is defined in main()
    //=====================================================================

    // This static method is used by main() only
    private static void evaluateExprTest(String s, LispExpressionEvaluator expr, String expect)
    {
        Double result;
        System.out.println("Expression " + s);
        System.out.printf("Expected result : %s\n", expect);
	expr.reset(s);
        try {
           result = expr.evaluate();
           System.out.printf("Evaluated result : %.2f\n", result);
        }
        catch (LispExpressionException e) {
            System.out.println("Evaluated result :"+e);
        }
        
        System.out.println("-----------------------------");
    }

    // define few test cases, exception may happen
    /*public static void main (String args[])
    {
        LispExpressionEvaluator expr= new LispExpressionEvaluator();
        //expr.setDebug();
        String test1 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)) (+ 1))";
        String test2 = "(+ (- 632) (* 21 3 4) (/ (+ 32) (* 1) (- 21 3 1)) (+ 0))";
        String test3 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 1) (- 2 1 ))(* 1))";
        String test4 = "(+ (/2)(+ 1))";
        String test5 = "(+ (/2 3 0))";
        String test6 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 3) (- 2 1 ))))";
        String test7 = "(+ (*))";
        String test8 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)) (+ ))";
	evaluateExprTest(test1, expr, "17.50");
	evaluateExprTest(test2, expr, "-378.12");
	evaluateExprTest(test3, expr, "4.50");
	evaluateExprTest(test4, expr, "1.5");
	evaluateExprTest(test5, expr, "Infinity or LispExpressionException");
	evaluateExprTest(test6, expr, "LispExpressionException");
	evaluateExprTest(test7, expr, "LispExpressionException");
	evaluateExprTest(test8, expr, "LispExpressionException");
    }*/   
}
