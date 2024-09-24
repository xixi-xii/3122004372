package service;
import java.util.Random;

public class Create {


    /**
     * 求最大公因数
     * @param a 分母
     * @param b 分子
     * @return b 最大公因数
     */
    public int comFactor(int a, int b) {
        while(true)
        {
            if(a%b == 0)return b;//分母能被分子整除，则分子就是最大公因数
            int temp = b;
            b = a%b;
            a = temp;
        }
    }


    /**
     * 生成运算式
     * @param r 运算数范围
     * @return ansFormula 含有后缀表达式、运算结果和运算式的字符串数组
     */
    public String[] createFormula(int r){
        Random random = new Random();
        String[] operator = {"+","-","×","÷","="};

        //用字符串数组装随机生成的运算符和运算数
        int operationNum = 1 + random.nextInt(3);//随机生成运算符个数（1~3)
        String[] totalO = new String[operationNum];
        int numberNum = operationNum + 1;//运算数个数 = 运算符个数 + 1
        String[] totalF = new String[numberNum];
        String formula = new String();//生成运算式

        //是否有分数
        Boolean hasFraction = false;

        //随机产生操作数：
        for (int i = 0;i < numberNum; i++) {

            //随机生成0或1用于确定生成分数还是整数
            int fractionOrNot = random.nextInt(2);

            if (fractionOrNot == 0) { //随机数为0，生成整数
                int integralPart = random.nextInt(r+1);
                totalF[i] = String.valueOf(integralPart);
            } else { //随机数只有0和1，这里else就代表随机数是1，生成分数，分数与整数的生成几率相同
                int denominator = 1+random.nextInt(r);//分母（+1是为了分母不为0）
                int molecule = random.nextInt(denominator);//分子部分要小于分母
                int integralPart = random.nextInt(r);//带分数的整数部分

                //化简分数
                if (molecule != 0) {//分子不为0，需进行化简
                    int commonFactor = comFactor(denominator, molecule);//求最大公因数
                    denominator /= commonFactor;//化简分母
                    molecule /= commonFactor;//化简分子
                }

                //输出最简分数
                if (integralPart == 0 && molecule > 0) { //整数部分为0,且分子大于0,不需要输出带分数形式
                    totalF[i] = molecule + "/" + denominator;
                    hasFraction = true;
                }
                else if (molecule == 0) {//分子为0，分数变成整数
                    totalF[i] = String.valueOf(integralPart);
                    hasFraction = false;
                }
                else {//输出带分数形式
                    totalF[i] = integralPart + "'" + molecule + "/" + denominator;
                    hasFraction = true;
                }
            }
        }

        //随机生成运算符：
        for (int i = 0;i < operationNum; i++) {
            if (hasFraction)                                   //为了简化运算
                totalO[i] = operator[random.nextInt(2)];//有分数则只进行加减运算
            else
                totalO[i] = operator[random.nextInt(4)];//没有分数则可以进行加减乘除运算
        }

        //choose的作用是为了随机选择一个位置放置括号
        int choose = numberNum;//先初始化为运算数个数的值
        if (numberNum > 2 )//如果运算数个数不止2个，就取[0,numberNum)中的随机整数
            choose = random.nextInt(numberNum);

        //生成运算式
        for (int i = 0;i < numberNum;i++) {
            if (i == choose && choose < operationNum) {//如果当前运算数是choose，且小于运算符数就在运算数前加上一个左括号
                formula = formula + "(" + totalF[i] + totalO[i] ;
            } else if (i == numberNum - 1 && i == choose+1 && choose<operationNum) {//如果当前运算数是最后一个运算数，并且是choose+1,并且choose小于运算符数，就在运算数和等号之间加上一个右括号
                formula = formula + totalF[i] + ")" + "=";
            } else if (i == choose + 1 && choose < operationNum) {//如果当前运算数不是最后一个，但是choose+1并且choose小于运算符数，就只在运算数后加上一个右括号
                formula = formula + totalF[i] + ")" + totalO[i];
            } else if (i == numberNum - 1) {//轮到最后一个运算数时，在运算数右边加上一个等号
                formula = formula + totalF[i] + "=";
            } else {//否则，就让运算数和运算符直接连接
                formula = formula + totalF[i] + totalO[i];
            }
        }

        //用字符串数组装计算好结果的答案集
        Cal checkAns = new Cal();
        String[] ansFormula = checkAns.checkout(formula,3*operationNum+3);//1个运算符有2个运算数+“=”+结果+运算式；   2个运算符有3个运算数+前两个数的结果+“=”+最终结果+运算式。。。
//        String[] ansFormula = {formula};//测试用（返回运算式字符串数组）
        if (ansFormula != null)
            return ansFormula;
        return null;
    }

}
