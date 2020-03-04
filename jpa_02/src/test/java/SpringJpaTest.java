import com.tengfei.dao.CustomerDao;
import com.tengfei.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangtf
 * @date 2020-03-04 10:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringJpaTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void  testFindJPQL() {
        Customer customer = customerDao.findOne(3L);
        System.out.println(customer);
    }

    @Test
    public void testFindAll() {
        List<Customer> all = customerDao.findAll();
        for(Customer customer : all) {
            System.out.println(customer);
        }
    }

    @Test
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustName("张腾达");
        customer.setCustAddress("安徽省阜阳市");
        customer.setCustPhone("13777852545");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate() {
        //根据id查询id为1的客户
        Customer customer = customerDao.findOne(5l);
        //修改客户名称
        customer.setCustName("传智播客顺义校区");
        //更新
        customerDao.save(customer);
    }

    /**
     * 根据id删除：调用delete(id)方法
     */
    @Test
    public void testDelete() {
        customerDao.delete(1l);
    }

    /**
     * 根据id查询：调用findOne(id)方法
     */
    @Test
    public void testFindById() {
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }

    @Test
    public void testFindAllCustomer() {
        List<Customer> all = customerDao.findAllCustomer();
        for(Customer customer : all) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindCustomerByName() {
        String name = "张腾达";
        Customer customerByName = customerDao.findCustomerByName(name);
        System.out.println(customerByName);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomerNameById() {
        customerDao.updateCustomerNameById("zhangsan",3L);
    }

    @Test
    public void testFindAllCutomerBySql() {
        List<Customer> allCutomerBySql = customerDao.findAllCutomerBySql();
        for(Customer customer:allCutomerBySql) {
            System.out.println(customer);
        }
    }
}
