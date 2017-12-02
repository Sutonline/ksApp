package android.kevin.cn.ks.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * up word
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/28.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpWord {

    private Long id;
    // word
    private String word;
    // 创建时间
    private Date createTime;
    // 是否删除
    private Integer status;

}
