package cn.ks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yongkang.zhang
 * @date 2017/12/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vps {

    private Integer id;
    private String name;
    private String desc;
}
