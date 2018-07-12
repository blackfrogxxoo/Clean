package me.shaoown.clean;

import java.util.Date;

import me.shaoown.clean.base.IAction1Request;
import me.shaoown.clean.base.IClient;
import me.shaoown.clean.base.IAction0Request;
import me.shaoown.clean.base.IRequest;
import me.shaoown.clean.base.IUi;
import me.shaoown.clean.rx.RxBus;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by black on 2017/3/21.
 */

public class TestDemo {

    public static void testAction0Request() {
        IClient client = new IClient() {
            private Subscription suscription;
            @Override
            public void register() {
                suscription = RxBus.INSTANCE.toObservable()
                        .subscribe(data -> {
                            if(data instanceof IAction0Request) {
                                System.out.println("receive request");
                                ((IAction0Request) data).getAction0().call();
                            }
                        });
            }

            @Override
            public void unregister() {
                if(suscription != null) {
                    suscription.unsubscribe();
                }
            }
        };
        client.register();
        IUi<IRequest> ui = RxBus.INSTANCE::post;
        ui.request((IAction0Request) () -> (Action0) () -> System.out.println("receive response"));
    }

    public static void testAction1Request() {
        IClient client = new IClient() {
            private Subscription suscription;
            @Override
            public void register() {
                suscription = RxBus.INSTANCE.toObservable()
                        .subscribe(data -> {
                            if(data instanceof IAction1Request) {
                                System.out.println("receive request");
                                ((IAction1Request) data).getAction1().call(new Date());
                            }
                        });
            }

            @Override
            public void unregister() {
                if(suscription != null) {
                    suscription.unsubscribe();
                }
            }
        };
        client.register();
        IUi<IRequest> ui = RxBus.INSTANCE::post;
        ui.request(new IAction1Request() {
            @Override
            public Action1 getAction1() {
                return new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("receive response: " + o);
                    }
                };
            }
        });
    }
}
