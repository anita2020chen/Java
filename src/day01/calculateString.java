package day01;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class calculateString {
    public Integer parseStrCounter(String str) {
        return parseCal(reversePolish(str));
    }

    public String reversePolish(String str) {
        //定义一个字符数组，将表达式转换成一个一个字符
        char[] arr = str.toCharArray();
        //定义一个字符串数组，用来处理字符数组中的字符
        String[] myArr = new String[arr.length];
        //处理字符数组中的字符为操作符的情况下，添加权重，表示优先级
        for (int i = 0; i < arr.length; i++) {
            //如果字符为0-9，则不做处理直接加入到myArr字符串数组中
            if (arr[i] >= '0' && arr[i] <= '9') {
                myArr[i] = arr[i] + "";
            } else {
                if (arr[i] == '+' || arr[i] == '-') {
                    myArr[i] = arr[i] + "1";
                } else if (arr[i] == '*' || arr[i] == '/') {
                    myArr[i] = arr[i] + "2";
                } else if (arr[i] == '(') {
                    myArr[i] = arr[i] + "3";
                } else {
                    myArr[i] = arr[i] + "";
                }
            }
        }
        //list集合用来存放逆波兰表达式的输出结果
        List<String> list = new ArrayList<String>();
        //定义一个栈，用来处理操作符的优先级
        Stack<String> stack = new Stack<String>();
        //循环遍历整个表达式
        for (int i = 0; i < arr.length; i++) {
            //如果read是数字，则直接输出到list中
            if (arr[i] >= '0' && arr[i] <= '9') {
                list.add(arr[i] + "");
            } else if (arr[i] == '(') {
                stack.push(myArr[i]);
            } else if (arr[i] == ')') {
                while (!stack.get(stack.size() - 1).equals("(3")) {
                    list.add(stack.pop().substring(0, 1));
                }
                //弹出'('
                stack.pop();
            } else {
                if (stack.isEmpty()) {
                    stack.push(myArr[i]);
                } else {
                    //获取read字符的权重
                    int num1 = Integer.parseInt(myArr[i].substring(1));
                    //获取top字符的权重
                    int num2 = Integer.parseInt(stack.get(stack.size() - 1).substring(1));
                    //如果top字符的权重为3，说明是'('，这read直接进栈，或者权重read>top，也直接进栈
                    if (num2 == 3 || num1 > num2) {
                        stack.push(myArr[i]);
                    } else {
                        while (num1 <= num2 && num2 != 3) {
                            list.add(stack.pop().substring(0, 1));
                            //重新获取top的权重
                            if (stack.isEmpty()) {
                                break;
                            }
                            num2 = Integer.parseInt(stack.get(stack.size() -1).substring(1));
                        }
                        //将read压栈
                        stack.push(myArr[i]);
                    }
                }
            }
//            System.out.println("字符：" + arr[i]);
//            System.out.println("list: " + list.toString());
//            System.out.println("stack: " + stack.toString());
        }
        //表达式循环判断结束后，弹出栈中的所有元素，并输出到list集合中
        while (!stack.isEmpty()) {
            list.add(stack.pop().substring(0, 1));
        }
        //将list集合中的数据转为逆波兰表达式
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i);
        }
        return s;
    }

    public Integer parseCal(String str) {
        Stack<String> number = new Stack<String>();
        int length = str.length();
        //String[] operator = new String[length];
        for (int i = 0; i < length; i++) {
            switch (str.charAt(i)) {
                case '+':
                case '-':
                case '*':
                case '/':
                    String num1 = number.pop();
                    String num2 = number.pop();
                    Integer num3 = cal(num1, num2, str.charAt(i) + "");
                    number.push(num3 + "");
                    break;
                default:
                    number.push(str.charAt(i) + "");

            }
        }
        return Integer.parseInt(number.pop());
    }

    public Integer cal(String num1, String num2, String operator) {
        switch (operator) {
            case "+":
                return Integer.parseInt(num1) + Integer.parseInt(num2);
            case "-":
                return Integer.parseInt(num1) - Integer.parseInt(num2);
            case "*":
                return Integer.parseInt(num1) * Integer.parseInt(num2);
            case "/":
                return Integer.parseInt(num1) / Integer.parseInt(num2);
            default:
                throw new IllegalArgumentException("值为null");
        }
    }
}
