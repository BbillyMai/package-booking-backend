package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.PackageOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageOrderRepository extends JpaRepository<PackageOrder,String> {
}
