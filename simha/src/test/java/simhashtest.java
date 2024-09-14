import Common.simhash;
import Common.txtIO;
import org.junit.Test;


public class simhashtest {

    //获取字符串Hash测试
    @Test
    public void getHashTest() {
        String[] strings = {"一位", "真正", "的", "作家"};
        for (String string : strings) {
            String stringHash = simhash.getHash(string);
            System.out.println(stringHash.length());
            System.out.println(stringHash);
            System.out.println("=====================");
        }
    }

    //获取字符串SimHash测试
    @Test
    public void getSimHashTest() {
        String orig = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig.txt");
        String orig_add = txtIO.readTxt("C:\\Users\\test\\Desktop\\orig_0.8_add.txt");
        System.out.println(orig + ": \n" + simhash.getsimhash(orig));
        System.out.println("=====================");
        System.out.println(orig_add + ": \n" + simhash.getsimhash(orig_add));
        System.out.println("=====================");
    }
}
