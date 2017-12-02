package android.kevin.cn.ks.data.repository;

import android.kevin.cn.ks.common.Result;
import android.kevin.cn.ks.domain.Plan;

import java.util.List;
import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @author yongkang.zhang
 * @date 2017/11/30
 */
public interface PlanRepository {

    @GET("listAllPlan")
    Observable<Result<List<Plan>>> listAll();

    @PUT("changePlan/{planId}")
    Observable<Result<Boolean>> changePlan(@Path(value = "planId") Long planId);

    @PUT("giveUp/{planId}")
    Observable<Result<Boolean>> giveUp(@Path(value = "planId") Long planId);

    @PUT("updatePlan")
    Observable<Result<Boolean>> updatePlan(@Body Plan plan);

    @GET("listRecentPlan")
    Observable<Result<List<Plan>>> listRencentPlan();

    @PUT("deleteRecentPlan")
    Observable<Result<List<Plan>>> deleteRecentPlan();



}
