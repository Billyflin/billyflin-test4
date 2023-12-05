package com.example.test4.service;

import com.example.test4.exception.ValidationException;
import com.example.test4.models.Producto;
import com.example.test4.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void crearProducto(Producto producto) throws Exception {
        if (!Pattern.matches("^[A-Z]\\d{10}$", producto.getCodigoProducto())) {
            throw new ValidationException("El código del producto debe comenzar con la inicial de la Marca del automóvil en mayúscula, seguido de la fecha y hora de creación.");
        }
        if (producto.getNombre().length() > 30) {
            throw new ValidationException("El nombre debe contener a lo más 30 letras");
        }
        if (producto.getStock() < 0) {
            throw new ValidationException("El stock debe ser numérico");
        }
        if (producto.getPrecio() < 0) {
            throw new ValidationException("El precio debe ser numérico");
        }
        if (!Pattern.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-[1-9kK]$", producto.getRutProveedor())) {
            throw new ValidationException("Debe ser un rut válido");
        }
        if (!Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", producto.getMailProveedor())) {
            throw new ValidationException("Debe tener un formato válido de mail");
        }
        productoRepository.save(producto);
    }
}
