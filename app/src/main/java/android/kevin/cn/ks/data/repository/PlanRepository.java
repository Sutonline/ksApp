package android.kevin.cn.ks.data.repository;

import android.kevin.cn.ks.common.Result;
import android.kevin.cn.ks.domain.Plan;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author yongkang.zhang
 * @date 2017/11/30
 */
public interface PlanRepository {

    @GET("app/listAllPlan")
    Observable<Result<List<Plan>>> listAll();

}
