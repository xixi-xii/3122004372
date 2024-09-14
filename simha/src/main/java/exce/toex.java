package exce;

public class toex extends RuntimeException{

        private String errMessage;

        public toex() {
            super();
        }

        public toex(String errMessage) {
            super(errMessage);
            this.errMessage = errMessage;
        }

        public String getErrMessage() {
            return errMessage;
        }

        public static void cast() {
            throw new toex("文本字符长度不能少于250字");
        }


    }
