package me.shaoown.clean.demo;

import me.shaoown.clean.base.IAction1Request;
import rx.functions.Action1;

/**
 * Created by black on 2017/3/21.
 */

public class MockRequest implements IAction1Request {
    @Override
    public Action1 getAction1() {
        return new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("MockRequest' response: " + o);
            }
        };
    }
}
