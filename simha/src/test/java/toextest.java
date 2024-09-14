import Common.simhash;
import org.junit.Test;


public class toextest {
    @Test
    public void shortStringExceptionTest() {
        //测试str.length()<250的情况
        System.out.println(simhash.getsimhash("918674"));
    }

    @Test
    public void longStringExceptionTest() {
        //测试str.length()>250的情况
        String str = "我比现在年轻十岁的时候，获得了一个游手好闲的职业，去乡间收集民间歌谣。那一年的整个夏天，我如同一只乱飞的麻雀，" +
                "我头戴宽边草帽，脚上穿着拖鞋，一条毛巾挂在身后的皮带上，让它像尾巴似的拍打着我的屁股。我整日张大嘴巴打着呵欠，散漫地走在田间小道上，我的拖鞋吧哒吧哒，把那些小道弄得尘土飞扬，" +
                "于是村里人就知道那个会讲荤故事会唱酸曲的人又来了。其实所有的荤故事所有的酸曲都是从他们那里学来的，我知道他们全部的兴趣在什么地方，自然这也是我的兴趣。我曾经遇到一个哭泣的老人，他鼻青眼肿地坐在田埂上，满腹的悲哀使他变得十分激动，看到我走来他仰起脸哭声更为响亮。我问他是谁把他打成这样的？他手指挖着裤管上的泥巴，愤怒地告诉我是他那不孝的儿子，当我再问为何打他时，他支支吾吾说不清楚了，我就立刻知道他准是对儿媳干了偷鸡摸狗的勾当。还" +
                "这位比现在年轻十岁的我，躺在树叶和草丛中间，睡了两个小时。其间有几只蚂蚁爬到了我的腿上，我沉睡中的手指依然准确地将它们弹走。后来仿佛是来到了水边，一位老人撑着竹筏在远处响亮地吆喝。" +
                "因为路途遥远，不愿去做皇帝的女婿。老人的自鸣得意让我失声而笑。";
        System.out.println(simhash.getsimhash(str));
        System.out.println(str.length());
    }

}
