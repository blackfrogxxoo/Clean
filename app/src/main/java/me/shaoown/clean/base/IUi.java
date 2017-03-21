package me.shaoown.clean.base;

/**
 * Created by black on 2017/3/21.
 */

public interface IUi<E extends IRequest> {
    void request(E data);
}
