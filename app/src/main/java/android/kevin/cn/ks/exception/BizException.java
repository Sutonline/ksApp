package android.kevin.cn.ks.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yongkang.zhang
 * @date 2017/12/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends Exception {

    private String code;
    private String displayMessage;

    public BizException(String message) {
        super(message);
    }

    public BizException(String code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public BizException(String message, String code, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }
}
