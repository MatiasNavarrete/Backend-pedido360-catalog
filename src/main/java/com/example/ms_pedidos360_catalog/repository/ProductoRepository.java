package com.example.ms_pedidos360_catalog.repository;

import com.example.ms_pedidos360_catalog.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}