package com.example.ms_pedidos360_catalog.controller;

import com.example.ms_pedidos360_catalog.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ms_pedidos360_catalog.repository.ProductoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/productos")
public class CatalogoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}/reducir-stock")
    public ResponseEntity<?> reducirStock(@PathVariable Long id, @RequestParam Integer cantidad) {
        return productoRepository.findById(id).map(producto -> {
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);
                return ResponseEntity.ok().body("Stock actualizado");
            } else {
                return ResponseEntity.badRequest().body("Stock insuficiente");
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}