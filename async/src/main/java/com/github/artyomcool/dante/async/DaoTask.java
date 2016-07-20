package com.github.artyomcool.dante.async;

import com.github.artyomcool.dante.core.dao.DaoMaster;

public interface DaoTask<T> {

    T execute(DaoMaster master) throws Exception;

    void onSuccess(T result);

    void onError(Throwable t);

}
