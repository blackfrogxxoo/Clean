package me.shaoown.clean;

import java.util.List;

import me.shaoown.clean.base.IAction1Request;
import rx.functions.Action1;

/**
 * Created by black on 2017/3/21.
 */

public class StringListRequest extends BaseRequest {
    private Action1<List<String>> action1;
    public StringListRequest(Action1<List<String>> action1) {
        super(action1);
        this.action1 = action1;
    }
    @Override
    public Action1<List<String>> getAction1() {
        return action1;
    }
}
