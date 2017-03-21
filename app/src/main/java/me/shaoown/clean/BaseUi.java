package me.shaoown.clean;

import me.shaoown.clean.base.IRequest;
import me.shaoown.clean.base.IUi;

/**
 * Created by black on 2017/3/22.
 */

public class BaseUi<E extends IRequest> implements IUi<E> {
    @Override
    public void request(E data) {

    }
}
