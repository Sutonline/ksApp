package android.kevin.cn.ks.domain;

import java.util.Date;

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

    private Long planId;

    private String name;

    private String desc;

    private Date createTime;

    private Integer status;

    private Integer keepDays;

    private Integer giveCnt;

    private Integer successUps;

    private Integer longestDays;

    private Date checkDate;
}
