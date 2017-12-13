package cn.ks.data.manage;

import cn.ks.common.Result;
import cn.ks.data.repository.PlanRepository;
import cn.ks.domain.PlanHistory;
import cn.ks.domain.Plan;
import cn.ks.util.RetrofitServiceFactory;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author yongkang.zhang
 * @date 2017/11/29
 */
public class PlanDataManager {

    private PlanRepository planRepository = RetrofitServiceFactory.getServiceBean(PlanRepository.class);

    public Observable<Result<List<Plan>>> listAll() {
        return planRepository.listAll();
    }

    public Observable<Result<Boolean>> changePlan(Long planId) {
        return planRepository.changePlan(planId);
    }

    public Observable<Result<Boolean>> giveUp(Long planId) {
        return planRepository.giveUp(planId);
    }

    public Observable<Result<Boolean>> updatePlan(Plan plan) {
        return planRepository.updatePlan(plan);
    }

    public Observable<Result<List<Plan>>> listRencentPlan() {
        return planRepository.listRencentPlan();
    }

    public Observable<Result<Boolean>> deleteRecentPlan() {
        return planRepository.deleteRecentPlan();
    }

    public Observable<Result<Plan>> getCurrent() {
        return planRepository.getCurrent();
    }

    public Observable<Result<Boolean>> isCheck(Long planId, String checkDate) {
        return planRepository.isCheck(planId, checkDate);
    }

    public Observable<Result<Boolean>> check(Plan plan){
        return planRepository.check(plan);
    }

    public Observable<Result<List<PlanHistory>>> listHistory(Long planId) {
        return planRepository.listHistory(planId);
    }
}
