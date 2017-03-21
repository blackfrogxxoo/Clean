package me.shaoown.clean;

import java.util.List;

import me.shaoown.clean.base.IAction1Request;
import rx.functions.Action1;

/**
 * Created by black on 2017/3/21.
 */

public class BaseRequest implements IAction1Request {
    private final Action1<?> action1;
    public BaseRequest(Action1<?> action1) {
        this.action1 = action1;
    }
    @Override
    public Action1<?> getAction1() {
        return action1;
    }
}
