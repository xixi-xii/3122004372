package Common;

import exce.txtex;

import java.io.*;


public class txtIO {


    public static String readTxt(String txtPath) {
        //判断文件类型是否匹配
        if (!txtPath.endsWith(".txt")) {
            txtex.cast();
        }

        String str = "";
        String strLine;

        //将txt文件按行读入 str中
        File file = new File(txtPath);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {

            //字符串拼接
            while ((strLine = bufferedReader.readLine()) != null) {
                str += strLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }


    public static void writeTxt(String txtElem, String txtPath) {
        //判断文件类型是否匹配
        if (!txtPath.endsWith(".txt")) {
            txtex.cast();
        }

        File file = new File(txtPath);
        try (FileWriter fileWriter = new FileWriter(file, true);) {
            fileWriter.write(txtElem);
            fileWriter.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}