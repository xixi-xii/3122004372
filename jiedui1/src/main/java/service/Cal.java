package service;
import java.util.HashMap;
import java.util.Stack;

public class Cal {

    public String[] checkout(String formula, int length) {
        Stack<String> stackNumber = new Stack<>();//用栈来存放运算数
        Stack<String> stackOp = new Stack<>();//用栈来存放运算符
        String[] post = new String[length];//后缀表达式
        // 哈希表存放运算符优先级
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("(", 0);
        hashmap.put("+", 1);
        hashmap.put("-", 1);
        hashmap.put("×", 2);
        hashmap.put("÷", 2);

        for (int i = 0, j = 0; i < formula.length(); ) {
            StringBuilder number = new StringBuilder();//数字
            //将式子分割
            char c = formula.charAt(i);  //把运算式的第i个字符赋值给c
            //若c为数字，存入number
            while (Character.isDigit(c) || c == '/' || c == '\'') { //数字、分数符号、带分数符号都要
                number.append(c);
                i++;
                c = formula.charAt(i);
            }
            //digit里无数字，开始处理符号
            if (number.length() == 0) {
                switch (c) {
                    //如果是“(”，就转化为字符串压入字符栈
                    case '(': {
                        stackOp.push(String.valueOf(c));
                        break;
                    }
                    //遇到“)”了，则进行计算
                    case ')': {
                        String operator = stackOp.pop();
                        //符号栈不为空，取操作数运算
                        while (!stackOp.isEmpty() && !operator.equals("(")) {
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();
                            //后缀表达式变形
                            post[j++] = a;
                            post[j++] = b;
                            post[j++] = operator;
                            String ansString = calculate(b, a, operator);
                            if (ansString == null)
                                return null;
                            //将结果压入栈
                            stackNumber.push(ansString);
                            //符号指向下一个计算符号
                            operator = stackOp.pop();
                        }
                        break;
                    }
                    //遇到了“=”，计算最终结果
                    case '=': {
                        String operator;
                        while (!stackOp.isEmpty()) {
                            operator = stackOp.pop();
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();
                            //后缀表达式变形
                            post[j++] = a;
                            post[j++] = b;
                            post[j++] = operator;
                            String ansString = calculate(b, a, operator);
                            if (ansString == null)
                                return null;
                            stackNumber.push(ansString);
                        }
                        break;
                    }
                    //其他
                    default: {
                        String operator;
                        while (!stackOp.isEmpty()) {
                            operator = stackOp.pop();
                            if (hashmap.get(operator) >= hashmap.get(String.valueOf(c))) { //通过比较优先级来进行栈排序
                                String a = stackNumber.pop();
                                String b = stackNumber.pop();
                                //后缀表达式变形
                                post[j++] = a;
                                post[j++] = b;
                                post[j++] = operator;
                                String answerString = calculate(b, a, operator);
                                if (answerString == null)
                                    return null;
                                stackNumber.push(answerString);
                            } else {
                                stackOp.push(operator);
                                break;
                            }

                        }
                        stackOp.push(String.valueOf(c));  //将符号压入符号栈
                        break;
                    }
                }
            }
            //处理数字，直接压栈
            else {
                stackNumber.push(number.toString());
                continue;
            }
            i++;
        }
        //栈顶数字为答案
        post[length - 3] = "=";
        post[length - 2] = stackNumber.peek();
        post[length - 1] = formula;
        return post;
    }


    /**
     * 化简分数
     * @param integralPart 整数部分
     * @param molecule 分子
     * @param denominator 分母
     * @return ansFormula 运算结果
     */
    private String greatFraction(int integralPart, int molecule, int denominator) {
        String answerFormula;
        int comFactor = 1;

        //求最大公约数
        Create create = new Create();
        comFactor = create.comFactor(denominator, molecule);

        //化简分数
        denominator /= comFactor;
        molecule /= comFactor;

        //带分数
        if (integralPart == 0 && molecule > 0) {
            answerFormula = String.valueOf(molecule) + '/' + String.valueOf(denominator);
        } else if (molecule == 0)
            answerFormula = String.valueOf(integralPart);
        else {
            answerFormula = String.valueOf(integralPart) + "'" + String.valueOf(molecule) + '/' + String.valueOf(denominator);
        }
        return answerFormula;
    }



    /**
     * 计算式子
     *
     * @param m        左运算数
     * @param n        右运算数
     * @param operator 运算符
     * @return answerFormula 运算结果
     */
    private String calculate(String m, String n, String operator) {
        String answerFormula = null;
        char op = operator.charAt(0);
        int[] indexFraction = {m.indexOf('\''), m.indexOf('/'), n.indexOf('\''), n.indexOf('/')};//分数 各部分 切割位置

        //处理分数运算
        if (indexFraction[1] > 0 || indexFraction[3] > 0) {
            int[] denominator = new int[3];
            int[] molecule = new int[3];
            int[] integralPart = new int[3];

            //切割分数
            if (indexFraction[1] > 0) {
                for (int i = 0; i < m.length(); i++) {
                    if (i < indexFraction[0]) {
                        integralPart[0] = Integer.parseInt(integralPart[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[0] && i < indexFraction[1]) {
                        molecule[0] = Integer.parseInt(molecule[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[1]) {
                        denominator[0] = Integer.parseInt(denominator[0] + String.valueOf(m.charAt(i) - '0'));
                    }
                }
            } else {
                integralPart[0] = Integer.parseInt(m);
                denominator[0] = 1;
                molecule[0] = 0;
            }

            if (indexFraction[3] > 0) {
                for (int i = 0; i < n.length(); i++) {
                    if (i < indexFraction[2]) {
                        integralPart[1] = Integer.parseInt(integralPart[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[2] && i < indexFraction[3]) {
                        molecule[1] = Integer.parseInt(molecule[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[3]) {
                        denominator[1] = denominator[1] + n.charAt(i) - '0';
                    }
                }
            } else {
                integralPart[1] = Integer.parseInt(n);
                denominator[1] = 1;
                molecule[1] = 0;
            }

            //分数运算
            switch (op) {
                case '+': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integralPart[0] * denominator[2] + molecule[0] * denominator[1] + integralPart[1] * denominator[2] + molecule[1] * denominator[0];
                    break;
                }
                case '-': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integralPart[0] * denominator[2] + molecule[0] * denominator[1] - integralPart[1] * denominator[2] - molecule[1] * denominator[0];
                    break;
                }
                default:
                    return null;
            }

            //提取整数部分
            if (molecule[2] >= denominator[2] && molecule[2] > 0) {
                integralPart[2] = molecule[2] / denominator[2];
                molecule[2] = Math.abs(molecule[2] % denominator[2]);
            } else if (molecule[2] < 0) {
                return null;
            }

            //化简分数
            if (molecule[2] != 0) {
                answerFormula = greatFraction(integralPart[2], molecule[2], denominator[2]);
            } else answerFormula = String.valueOf(integralPart[2]);

        } else { //处理整数运算
            int a = Integer.parseInt(m);
            int b = Integer.parseInt(n);

            switch (op) {
                case '+': {
                    answerFormula = String.valueOf(a + b);
                    break;
                }
                case '-': {
                    if (a - b >= 0)
                        answerFormula = String.valueOf(a - b);
                    else
                        return null;
                    break;
                }
                case '×': {
                    answerFormula = String.valueOf(a * b);
                    break;
                }
                case '÷': {
                    if (b == 0) {//分母为0，异常
                        return null;
                    } else if (a % b != 0) {//不能整除
                        answerFormula = a % b + "/" + b;
                        if (a / b > 0) answerFormula = a / b + "'" + answerFormula;//可以化简分数
                    } else//能整除
                        answerFormula = String.valueOf(a / b);
                    break;
                }
            }
        }
        return answerFormula;
    }

}
