package service;

import java.util.ArrayList;
import java.util.Arrays;

public class Check {
    private ArrayList<String> returnList = new ArrayList<>();
    private ArrayList<String> txtList = new ArrayList<>();
    private ArrayList<String> answerList = new ArrayList<>();
    private ArrayList<String[]> answerFormulaList = new ArrayList<>();


    /**
     * 生成暂存习题和答案，暂存的目的是提前检查运算式是否有重复
     * @param n 运算题数量
     * @param r 运算数范围
     * @return returnList 返回没有重复的运算式集（还含有未标序号的题和答案）
     */
    public ArrayList generate(int n,int r) {
        Create create = new Create();
        //生成n条不重复的运算式
        for(int i = 0 ; i < n;){
            //随机生成运算式
            String[] answerFormula = create.createFormula(r);
            //判断生成的运算式是否重复
            if (answerFormula != null) {
                if (!ifRepeat(answerFormula)) {
                    System.out.println((i+1)+":"+ answerFormula[answerFormula.length-1]);
                    i++;
                }
            }
        }

        //把式子及运算结果添加到returnList
        for (int i = 0;i < 2*n;i++) {
            if(i < n) {
                returnList.add(txtList.get(i));
            } else {
                returnList.add(answerList.get(i-n));
            }
        }
        return returnList;
    }


    /**
     * 判断运算式是否重复
     * @param answerFormula 含有后缀表达式、运算结果和运算式的字符串数组
     * @return true（重复） / false（不重复）
     */
    private boolean ifRepeat(String[] answerFormula) {
        String formula = answerFormula[answerFormula.length-1];
        String[] rPNotation = new String[answerFormula.length-1];
        System.arraycopy(answerFormula, 0, rPNotation, 0, answerFormula.length-1);
        boolean ifRepeat = false;

        for (String[] answerFo : answerFormulaList) {
            if (Arrays.equals(answerFo,rPNotation)) {
                ifRepeat = true;
            } else if (answerFo.length == rPNotation.length && answerFo[answerFo.length-1].equals(rPNotation[rPNotation.length-1])){//若运算结果及长度一致，则式子可能重复，进一步比较
                int j;
                for (j=0;j<rPNotation.length-2;) {
                    boolean opRight = answerFo[j+2].equals("＋")|| answerFo[j+2].equals("×");
                    boolean exRight = answerFo[j].equals(rPNotation[j + 1]) && answerFo[j + 1].equals(rPNotation[j]) && answerFo[j + 2].equals(rPNotation[j + 2]);
                    boolean copRight = answerFo[j].equals(rPNotation[j]) && answerFo[j + 1].equals(rPNotation[j + 1]) && answerFo[j + 2].equals(rPNotation[j + 2]);
                    //运算符前后两个操作数交换比较
                    if (exRight&&opRight) {
                        j = j + 3;
                    } else if (copRight) {
                        j = j + 3;
                    } else {
                        break;
                    }
                }
                if (j == rPNotation.length-2) {
                    ifRepeat = true;
                    break;
                }
            }
        }

        if (!ifRepeat) {    //运算式不重复
            this.txtList.add(formula);
            this.answerList.add(rPNotation[rPNotation.length-1]);
            this.answerFormulaList.add(rPNotation);
        }
        return ifRepeat;
    }
}
