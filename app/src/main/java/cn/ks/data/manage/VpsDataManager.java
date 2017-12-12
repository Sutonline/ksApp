package cn.ks.data.manage;

import cn.ks.common.Result;
import cn.ks.data.repository.VpsRepository;
import cn.ks.domain.VultrDomain;
import cn.ks.util.RetrofitServiceFactory;

import io.reactivex.Observable;


/**
 * @author yongkang.zhang
 * @date 2017/12/8
 */
public class VpsDataManager {

    private VpsRepository vpsRepository = RetrofitServiceFactory.getServiceBean(VpsRepository.class);

    public Observable<Result<VultrDomain>> getVultr() {
        return vpsRepository.getVultr(3);
    }

}
