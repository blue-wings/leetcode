package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: FR
 * Time: 10/15/14 9:49 AM
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class EvaluateReversePolishNotation {

    /**
     * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        List<String> operations = new ArrayList<String>();
        operations.add("+");
        operations.add("-");
        operations.add("*");
        operations.add("/");
        LinkedList<String> stack = new LinkedList<String>();
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for (String ele : tokens){
            stack.add(ele);
        }
        while (!stack.isEmpty()){
            String ele = stack.pop();
            if(operations.contains(ele)){
                int b = nums.pop();
                int a = nums.pop();
                int result;
                if(ele.equals("+")){
                    result = a+b;
                }else if(ele.equals("-")){
                    result = a-b;
                }else if(ele.equals("*")){
                    result = a*b;
                }else{
                    result = a/b;
                }
                nums.push(result);
            } else {
                nums.push(Integer.parseInt(ele));
            }
        }
        return nums.pop();
    }

    public static void main(String[] args){
        EvaluateReversePolishNotation e = new EvaluateReversePolishNotation();
        String[] arg = {"2", "1", "+", "3", "*"};
        System.out.println(e.evalRPN(arg));
        String[] arg1 = {"4", "13", "5", "/", "+"};
        System.out.println(e.evalRPN(arg1));
    }

}
