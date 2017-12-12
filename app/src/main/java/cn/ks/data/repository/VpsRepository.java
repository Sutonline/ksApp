package cn.ks.data.repository;

import cn.ks.common.Result;
import cn.ks.domain.VultrDomain;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author yongkang.zhang
 * @date 2017/12/8
 */
public interface VpsRepository {

    @GET("getVultr/{type}")
    Observable<Result<VultrDomain>> getVultr(@Path(value ="type") Integer type);
}
