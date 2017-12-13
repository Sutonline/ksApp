package cn.ks.data.repository;

import cn.ks.common.Result;
import cn.ks.domain.PlanHistory;
import cn.ks.domain.Plan;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Observable<Result<Boolean>> deleteRecentPlan();

    @GET("getCurrent")
    Observable<Result<Plan>> getCurrent();

    @GET("isCheck")
    Observable<Result<Boolean>> isCheck(@Query(value = "planId") Long planId, @Query(value = "checkDate") String checkDate);

    @PUT("check")
    Observable<Result<Boolean>> check(@Body Plan plan);

    @GET("listHistory/{planId}")
    Observable<Result<List<PlanHistory>>> listHistory(@Path(value = "planId") Long planId);
}
