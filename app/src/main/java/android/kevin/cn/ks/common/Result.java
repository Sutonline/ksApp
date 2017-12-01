package android.kevin.cn.ks.common;

import lombok.Data;

/**
 * @author yongkang.zhang
 * @date 2017/12/1
 */
@Data
public class Result<T> {

    /**
     * 0 错误
     * 1 成功
     */
    private String code;
    private String message;
    private T T;

}
