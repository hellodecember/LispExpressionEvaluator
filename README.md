# LispExpressionEvaluator
A calculator that solves Lisp Expressions. Comes with a GUI.

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
