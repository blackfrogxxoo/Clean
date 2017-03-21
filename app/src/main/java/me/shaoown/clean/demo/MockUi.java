package me.shaoown.clean.demo;

import me.shaoown.clean.base.IRequest;
import me.shaoown.clean.base.IUi;
import me.shaoown.clean.rx.RxBus;

/**
 * Created by black on 2017/3/21.
 */

public class MockUi implements IUi<IRequest> {

    @Override
    public void request(IRequest data) {
        if(data instanceof MockRequest) {
            RxBus.INSTANCE.post(data);
        }
    }
}
