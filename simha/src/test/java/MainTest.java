import Common.hamming;
import Common.simhash;
import Common.txtIO;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MainTest {

    //测试原文件与所有测试文件的相似度
    @Test
    public void origAndAllTest() {
        String[] str = new String[6];
        str[0] = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig.txt");
        str[1] = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_add.txt");
        str[2] = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_del.txt");
        str[3] = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_dis_1.txt");
        str[4] = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_dis_10.txt");
        str[5] = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_dis_15.txt");
        String ansFileName = "C:\\Users\\test\\Desktop\\text.txt";
        for (int i = 0; i <= 5; i++) {
            Double similarity = hamming.getSimilarity(simhash.getsimhash(str[0]), simhash.getsimhash(str[i]));
            //保留小数点后两位
            String resultSimilarity = String.format("%.2f", similarity);
            String result = "时间：" + DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss").format(LocalDateTime.now()) + "\n" + "原文件与文件" + i + "的相似度为：" + resultSimilarity + "\r\n";
            txtIO.writeTxt(result, ansFileName);
            System.out.println(result);
        }
    }
}
