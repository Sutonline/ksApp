package android.kevin.cn.ks.domain;

import java.util.Date;

import lombok.Data;

/**
 * @author yongkang.zhang
 * @date 2017/12/5
 */
@Data
public class PlanHistory {

    private Long planId;

    private Date checkDate;

    private Date createTime;
}
