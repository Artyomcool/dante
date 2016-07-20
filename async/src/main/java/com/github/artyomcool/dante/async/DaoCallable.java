package com.github.artyomcool.dante.async;

import com.github.artyomcool.dante.core.dao.DaoMaster;

public interface DaoCallable<T> {

    T call(DaoMaster master) throws Exception;

}
