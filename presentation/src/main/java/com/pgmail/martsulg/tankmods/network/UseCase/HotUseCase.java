package com.pgmail.martsulg.tankmods.network.UseCase;

import com.pgmail.martsulg.tankmods.entity.HotRoot;
import com.pgmail.martsulg.tankmods.network.RestService;

import io.reactivex.Observable;

/**
 * Created by g_washingt0n on 25.01.2018.
 */

public class HotUseCase extends UseCase<Object,HotRoot> {
    @Override
    protected Observable<HotRoot> buildUseCase(Object o) {
        return RestService.getInstance().getHot();
    }
}
