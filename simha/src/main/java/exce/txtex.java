package exce;

public class txtex extends RuntimeException {

    private String errMessage;

    public txtex() {
        super();
    }

    public txtex(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast() {
        throw new txtex("仅支持读取txt类型文件");
    }
}

