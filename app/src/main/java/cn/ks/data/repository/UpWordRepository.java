package cn.ks.data.repository;

import cn.ks.common.Result;
import cn.ks.domain.UpWord;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * upword repository
 * @author yongkang.zhang
 * @date 2017/12/2
 */
public interface UpWordRepository {

    @GET("listAllUpWord")
    Observable<Result<List<UpWord>>> listAll();

    @PUT("deleteUpWord/{id}")
    Observable<Result<Boolean>> deleteById(@Path("id") Long id);

    @PUT("save")
    Observable<Result<Boolean>> save(@Body UpWord word);
}
