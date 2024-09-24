package util;

import service.Check;

import java.io.*;
import java.util.ArrayList;


public class IOUtil {

    /**
     * 生成并输出Exercises.txt、Answer.txt
     * 创建运算式集
     * @param n 运算题数量
     * @param r 运算数范围
     */
    public void createProblemSet(int n,int r){
        Check temporarySet = new Check();
        ArrayList returnList = temporarySet.generate(n,r);
        ArrayList<String> txtList = new ArrayList<>();
        ArrayList<String> ansList = new ArrayList<>();

        for (int i = 0;i < 2*n;i++) {
            if(i < n) txtList.add(returnList.get(i).toString());
            else ansList.add(returnList.get(i).toString());
        }
        createExeFile(txtList);
        createAnsFile(ansList);
    }

    /**
     * 生成并输出Exercises.txt
     * @param txtList 题目
     */
    private void createExeFile(ArrayList txtList){
        try{
            File exTXT = new File("Exercises.txt");

            //若文件存在，删除文件
            if (exTXT.exists()) {
                exTXT.delete();
            }
            if(exTXT.createNewFile()){
                System.out.println("正在创建Exercises.txt");
                FileOutputStream txtFile = new FileOutputStream(exTXT);
                PrintStream q = new PrintStream(exTXT);

                for(int i = 0;i < txtList.size();i++){
                    q.println(i+1 + ". " +txtList.get(i));
                }
                txtFile.close();
                q.close();
                System.out.println("Exercises.txt 创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 生成并输出Answer.txt
     * @param ansList 答案
     */
    private void createAnsFile(ArrayList ansList){
        try{
            File ansTXT = new File("Answer.txt");

            //若文件存在，删除文件
            if (ansTXT.exists()) {
                ansTXT.delete();
            }

            if(ansTXT.createNewFile()){
                System.out.print("正在创建Answer.txt\n");
                FileOutputStream ansFile = new FileOutputStream(ansTXT);
                PrintStream p = new PrintStream(ansTXT);

                for(int i = 0;i < ansList.size();i++){
                    p.println(i+1 + ". " +ansList.get(i));
                }
                ansFile.close();
                p.close();
                System.out.println("Answer.txt 创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 生成并输出Grade.txt
     * @param submitPath 学生答案文件地址
     * @param answersPath 答案文件地址
     */
    public void createGradeFile(String submitPath, String answersPath) {
        try {

            ArrayList<String> submitList = obtainAnswer(submitPath);
            ArrayList<String> answersList = obtainAnswer(answersPath);
            ArrayList<String> WQuesNum = new ArrayList<>();
            ArrayList<String> TQuesNum = new ArrayList<>();

            for (int i = 0; i < submitList.size(); i++) {
                //对比答案
                if (submitList.get(i).equals(answersList.get(i)))
                    TQuesNum.add(String.valueOf(i+1));
                else
                    WQuesNum.add(String.valueOf(i+1));
            }

            File gradeTXT = new File("Grade.txt");

            //若文件存在，删除文件
            if (gradeTXT.exists()) {
                gradeTXT.delete();

            }

            if (gradeTXT.createNewFile()) {
                System.out.print("创建Grade.txt\n");
                FileOutputStream gradeFile = new FileOutputStream(gradeTXT);
                PrintStream p = new PrintStream(gradeTXT);

                p.print("Correct:");
                output(p, TQuesNum);
                p.print("Wrong:");
                output(p, WQuesNum);


                gradeFile.close();
                p.close();
                System.out.println("Grade.txt 创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 输出答案
     * @param p 打印输出流
     * @param quesNum 正确/错误的题号
     */
    private void output(PrintStream p,ArrayList quesNum) {
        p.print(quesNum.size() +"(");
        for(int i = 0;i < quesNum.size();i++){
            System.out.print(">");
            if (i < quesNum.size()-1)
                p.print(" " + quesNum.get(i) + "，");
            else
                p.print(" " + quesNum.get(i));
        }
        p.println(" )\n");
    }


    /**
     * 获取文件答案
     * @param path 文件地址
     * @return answerList 文件内容
     * @throws IOException
     */
    private ArrayList<String> obtainAnswer(String path) throws IOException {
        ArrayList<String> answerList = new ArrayList<>();
        BufferedReader answerFile = new BufferedReader(new FileReader(path));
        String answerLine = null;

        while ((answerLine = answerFile.readLine()) != null) {
            answerLine = answerLine.replace(" ", "");
            if (answerLine.indexOf('.') >= 0) {
                if (answerLine.length() > 2)
                    answerList.add(answerLine);
            }
        }
        return answerList;
    }
}
