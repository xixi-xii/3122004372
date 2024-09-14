import Common.hamming;
import Common.simhash;
import Common.txtIO;
import org.junit.Test;


public class hammingtest {

    //获取海明距离
    @Test
    public void getHammingDistanceTest() {
        String str0 = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig.txt");
        String str1 = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_add.txt");
        int distance = hamming.getHammingDistance(simhash.getsimhash(str0), simhash.getsimhash(str1));
        System.out.println("海明距离：" + distance);
        System.out.println("相似度: " + (100 - distance * 100 / 128) + "%");
    }

    //获取海明距离失败测试
    @Test
    public void getHammingDistanceFailTest() {
        // 测试str0.length()!=str1.length()的情况
        String str0 = "10101010";
        String str1 = "1010101";
        System.out.println(hamming.getHammingDistance(str0, str1));
    }

    //获取相似度测试
    @Test
    public void getSimilarityTest() {
        String str0 = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig.txt");
        String str1 = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_add.txt");
        int distance = hamming.getHammingDistance(simhash.getsimhash(str0), simhash.getsimhash(str1));
        double similarity = hamming.getSimilarity(simhash.getsimhash(str0), simhash.getsimhash(str1));
        System.out.println("str0和str1的汉明距离: " + distance);
        System.out.println("str0和str1的相似度:" + similarity);
    }
}
