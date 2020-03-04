package com.tengfei.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author zhangtf
 * @date 2020-03-03 21:36
 */
public class JpaUtil {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
