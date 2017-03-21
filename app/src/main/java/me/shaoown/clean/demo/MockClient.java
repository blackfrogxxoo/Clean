package me.shaoown.clean.demo;

import java.util.Date;

import me.shaoown.clean.base.IClient;
import me.shaoown.clean.rx.RxBus;
import rx.Subscription;

/**
 * Created by black on 2017/3/21.
 */

public class MockClient implements IClient {
    private Subscription subscription;

    @Override
    public void register() {
        subscription = RxBus.INSTANCE.toObserverable()
                .subscribe(o -> {
                    if(o instanceof MockRequest) {
                        ((MockRequest) o).getAction1().call(new Date());
                    }
                });
    }

    @Override
    public void unregister() {
        if(subscription != null) {
            subscription.unsubscribe();
        }
    }
}
