package com.tengfei.dao;

import com.tengfei.domain.Customer;
import javafx.scene.chart.ValueAxis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhangtf
 * @date 2020-03-04 10:05
 */
public interface CustomerDao extends JpaRepository<Customer,Long> {

    @Query(value = "from Customer ")
    public List<Customer> findAllCustomer();

    @Query(value = "from Customer where custName = ?1")
    public Customer findCustomerByName(String custName);

    @Query(value = "update Customer set custName = ?1 where custId = ?2")
    @Modifying
    public void updateCustomerNameById(String name,Long id);

    @Query(value = "select * from cst_customer", nativeQuery = true )
    public List<Customer> findAllCutomerBySql();

}
