package com.infosys.infytel.order.repository;


//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.infytel.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, String> {


}

