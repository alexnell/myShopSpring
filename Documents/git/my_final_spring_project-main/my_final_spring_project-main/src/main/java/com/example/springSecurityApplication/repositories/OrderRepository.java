package com.example.springSecurityApplication.repositories;

import com.example.springSecurityApplication.models.Order;
import com.example.springSecurityApplication.models.Person;
import com.example.springSecurityApplication.models.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);

//    List<Order> findByTitleContainingIgnoreCase(String name);

@Query(value = "select * from order where ((lower(title) LIKE '%?1')", nativeQuery = true)
List<Order> findByTitleGreaterThanEqual(String title);
}
