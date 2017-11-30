package android.kevin.cn.ks.data.repository;

import android.kevin.cn.ks.domain.Plan;

import java.util.List;

import retrofit2.http.GET;

/**
 * @author yongkang.zhang
 * @date 2017/11/30
 */
public interface PlanRepository {

    @GET("http://localhost/app/listAllPlan")
    List<Plan> listAll();

}
