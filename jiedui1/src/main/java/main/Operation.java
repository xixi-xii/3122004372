package main;
import util.IOUtil;
import java.util.Scanner;

public class Operation {
    /**
     * @param args 指令字符串数组
     */
    public static void main(String[] args) {
        ord:
        while(true) {
            //若不输入指令直接按回车键，则默认生成10条运算数在10以内的算术题

            int n = 10;//运算题数量，默认为10
            int r = 10;//运算数范围，默认为[0,10)
            String questionPath = null;//题目文件路径
            String answersPath = null;//答案文件路径

            try {
                // 输入指令
                System.out.println("请输入需要执行的指令：");
                Scanner command = new Scanner(System.in);
                String arr[] = command.nextLine().split("\\s"); //把输入的字符串以\\s为条件分割成一个String数组

                //指令数组长度>1时，获取正常指令
                if (arr.length > 1) {
                    for (int i = 0; i < arr.length; i = i + 2) {
                        switch (arr[i]) {
                                case "-r":
                                r = Integer.parseInt(arr[i + 1]);
                                if (r < 1) {
                                    System.out.println("输入的r范围错误，这是题目中的数值范围，请输入大于0的整数");
                                    return;
                                }
                                break;
                                case "-n":
                                n = Integer.parseInt(arr[i + 1]);
                                if (n > 10000 || n < 1) {
                                    System.out.println("输入的n范围错误，这是生成题目的数量，请输入大于0的整数");
                                    return;
                                }
                                break;
                                case "-a":
                                answersPath = arr[i + 1];
                                if (answersPath == null) {
                                    System.out.println("无法找到答案文件的路径");
                                    return;
                                }
                                break;
                                case "-e":
                                questionPath = arr[i + 1];
                                if (questionPath == null) {
                                    System.out.println("无法找到题目文件的路径");
                                    return;
                                }
                                break;
                                default:
                                System.out.println("输入的指令错误！");
                                break;
                        }
                    }
                }else if(arr[0].equals("exit")){//输入exit退出程序
                    System.out.println("退出程序");
                    System.exit(0);
                }
                else if(arr[0].length() != 0){//输入的不是任何指令时报错，重新输入指令
                    System.out.println("输入的指令错误！");
                    continue ord;
                }
            } catch (NumberFormatException e) {
                System.out.println("输入的指令错误！请重新输入");
                continue ord;
            }

            System.out.println("n: " + n + ", r: " + r);
            IOUtil makefile = new IOUtil();

            //文件不为空时，创建成绩文件；为空时，创建题目文件和答案文件
            if (questionPath != null && answersPath != null)
                makefile.createGradeFile(questionPath,answersPath);
            else
                makefile.createProblemSet(n,r);
        }
    }
}
