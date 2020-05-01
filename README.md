# LispExpressionEvaluator
An old CSC220 Project from SF State. Modified by me by adding a GUI to it.


### Specifications: 
Taken from Project 7, Chapter 5, Page 178<br />
I have modified specification and requirements of this project.

### Abstract:
In the language Lisp, each of the four basic arithmetic operators appears before an arbitrary number of operands, 
which are separated by spaces. The resulting expression is enclosed in parentheses. The operators behave as follows:
  - (+ a b c ...) returns the sum of all the operands, and (+ a) returns a.
  - (- a b c ...) returns a - b - c - ..., and (- a) returns -a. 
  - (* a b c ...) returns the product of all the operands, and (* a) returns a.
  - (/ a b c ...) returns a / b / c / ..., and (/ a) returns 1/a.<br />
  
Note: + * - / must have at least one operand

You can form larger arithmetic expressions by combining these basic expressions using a fully parenthesized prefix notation. 
For example, the following is a valid Lisp expression: <br />

(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)) (+ 1))<br />
  
This expression is evaluated successively as follows:<br />
    (+ (- 6) (* 2 3 4) (/ 3 1 -2) (+ 1))<br />
    (+ -6 24 -1.5 1.0)<br />
    17.5<br />
    
### Requirements:<br />
- Design and implement an algorithm that uses LinkedStack class to evaluate a valid Lisp expression composed of the four basic 
operators and integer values. 
- Valid tokens in an expression are '(',')','+','-','*','/',and positive integers (>=0)
- Display result as floting point number with at 2 decimal places
- Negative number is not a valid "input" operand, e.g. (+ -2 3)<br />
  However, you may create a negative number using parentheses, e.g. (+ (-2)3)
- There may be any number of blank spaces, >= 0, in between tokens<br />
     Thus, the following expressions are valid:<br />
     	(+   (-6)3)<br />
    	(/(+20 30))<br />

- Must use LinkedStack class in this project. (*** DO NOT USE Java API Stack class ***)
- Must throw LispExpressionException to indicate errors
- Must not add new or modify existing data fields
- Must implement these methods in LispExpressionEvaluator class: 

  	public LispExpressionEvaluator()<br />
  	public LispExpressionEvaluator(String inputExpression)<br /> 
    public void reset(String inputExpression)<br /> 
    public double evaluate()<br />
    private void evaluateCurrentOperation()<br />

- You may add new private methods
