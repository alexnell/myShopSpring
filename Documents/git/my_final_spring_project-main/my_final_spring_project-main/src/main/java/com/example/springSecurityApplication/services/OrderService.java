package com.example.springSecurityApplication.services;

import com.example.springSecurityApplication.enumm.Status;
import com.example.springSecurityApplication.models.Order;
import com.example.springSecurityApplication.models.Product;
import com.example.springSecurityApplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Данный метод позволяет вернуть все заказы
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    //Данный матод позволяет отредактировать заказы
    @Transactional
    public void updateOrder(int id, Order order){
        order.setId(id);
        orderRepository.save(order);
    }

//    Данный метод позволяет вернуть  по id
        public Order getOrderById(int id){
        Optional<Order> optionalOrder = orderRepository.findById (id);
        return optionalOrder.orElse(null);
    }
    //Данный метод позволяет удалить  по id
    @Transactional
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    //Данный метод позволяет вернуть товар по id
    public Order getOrderId(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    //Данный метод позволяет сохранить товар
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void setStatus(int id, String status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(Status.valueByName(status));
            orderRepository.save(order);
        }
    }

}
