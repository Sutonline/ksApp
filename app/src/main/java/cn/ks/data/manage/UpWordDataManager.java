package cn.ks.data.manage;

import cn.ks.common.Result;
import cn.ks.data.repository.UpWordRepository;
import cn.ks.domain.UpWord;
import cn.ks.util.RetrofitServiceFactory;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yongkang.zhang
 * @date 2017/12/2
 */
public class UpWordDataManager {

    private UpWordRepository repository = RetrofitServiceFactory.getServiceBean(UpWordRepository.class);

    public Observable<Result<List<UpWord>>> listAll() {
        return repository.listAll();
    }

    public Observable<Result<Boolean>> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Observable<Result<Boolean>> save(UpWord upWord) {
        return repository.save(upWord);
    }

}
