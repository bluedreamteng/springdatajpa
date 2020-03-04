import com.tengfei.domain.Customer;
import com.tengfei.util.JpaUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @author zhangtf
 * @date 2020-03-03 21:11
 */
public class JpaTest {


    @Test
    public void test() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Customer customer = new Customer();
            customer.setCustName("zhangtf");
            customer.setCustAddress("安徽省阜阳市");
            customer.setCustPhone("18133673728");
            entityManager.persist(customer);
            transaction.commit();
        } finally {
            entityManager.close();
        }
    }

    @Test
    public void testAdd() {
        // 定义对象
        Customer c = new Customer();
        c.setCustName("传智学院");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("IT教育");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 获取实体管理对象
            em = JpaUtil.getEntityManager();
            // 获取事务对象
            tx = em.getTransaction();
            // 开启事务
            tx.begin();
            // 执行操作
            em.persist(c);
            // 提交事务
            tx.commit();
        } catch (Exception e) {
            // 回滚事务
            assert tx != null;
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            assert em != null;
            em.close();
        }
    }

    @Test
    public void testMerge() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Customer customer = em.find(Customer.class, 3L);
            customer.setCustName("张腾飞");
            em.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Test
    public void testRemove() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Customer customer = em.find(Customer.class, 1L);
            em.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * 立即加载的策略
     */
    @Test
    public void testGetOne() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Customer customer = em.find(Customer.class, 3L);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * 立即加载的策略
     */
    @Test
    public void testCaching() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Customer customer1 = em.find(Customer.class, 3L);
            Customer customer2 = em.find(Customer.class, 3L);
            //EntityManager 也有缓存
            Assert.assertSame(customer1, customer2);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * 延迟加载策略
     */
    @Test
    public void testLoadOne() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Customer reference = em.getReference(Customer.class, 3L);
            //执行到此才发送语句
            System.out.println(reference);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Test
    public void testFindAll() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("from Customer ");
            List resultList = query.getResultList();
            for (Object object : resultList) {
                System.out.println(object);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Test
    public void testPaged() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("from Customer ");
            query.setFirstResult(1);
            query.setMaxResults(2);
            List resultList = query.getResultList();
            for (Object object : resultList) {
                System.out.println(object);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Test
    public void testFindCondition() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("from Customer order by custId desc ");
            List resultList = query.getResultList();
            for(Object object : resultList) {
                System.out.println(object);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Test
    public void testFindCount() {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("select count(custId) from Customer ");
            Object singleResult = query.getSingleResult();
            System.out.println(singleResult);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
