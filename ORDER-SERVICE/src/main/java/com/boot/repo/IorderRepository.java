package com.boot.repo;

import com.boot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IorderRepository extends JpaRepository<Order,Long> {
}
