package android.kevin.cn.ks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/9.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    private Long id;
    private String name;
    private String desc;
}
