package android.kevin.cn.ks.data.manage;

import android.kevin.cn.ks.common.Result;
import android.kevin.cn.ks.data.repository.PlanRepository;
import android.kevin.cn.ks.domain.Plan;
import android.kevin.cn.ks.util.RetrofitServiceFactory;

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

}
