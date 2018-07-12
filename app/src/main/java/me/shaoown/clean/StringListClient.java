package me.shaoown.clean;

import java.util.ArrayList;
import java.util.List;

import me.shaoown.clean.base.IAction1Request;
import me.shaoown.clean.base.IClient;
import me.shaoown.clean.rx.RxBus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by black on 2017/3/21.
 */

public class StringListClient implements IClient {
    private Subscription mSubscription;
    private int i = 0;
    List<String> strings = new ArrayList<>();
    @Override
    public void register() {
        mSubscription = RxBus.INSTANCE.toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        strings.add("Item " + i++);
                        ((IAction1Request) o).getAction1().call(strings);
                    }
                });
    }

    @Override
    public void unregister() {
        if(mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
