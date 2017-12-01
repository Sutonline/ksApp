package android.kevin.cn.ks.enums;

/**
 * @author yongkang.zhang
 * @date 2017/12/1
 */
public enum ResultTypeEnum {
    SUCCESS("1"), ERROR("0");

    private String code;

    ResultTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
