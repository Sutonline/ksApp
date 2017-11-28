package android.kevin.cn.ks.enums;

/**
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/28.
 */
public enum UpWordStatusEnum {

    ENABLE(1, "启用"), DISABLE(2, "禁用"), DELETE(3, "删除");


    private Integer code;
    private String desc;

    UpWordStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
