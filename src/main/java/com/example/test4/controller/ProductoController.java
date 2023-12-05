package com.example.test4.controller;

import com.example.test4.exception.ValidationException;
import com.example.test4.models.Producto;
import com.example.test4.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/crearProducto")
    public ResponseEntity<String> verificarProducto(@RequestBody String productoStr) {
        try {
            String[] campos = productoStr.replace("\n", "").replace("\r", "").split(",");
            if (campos.length != 6) {
                throw new ValidationException("Formato de entrada inválido");
            }
            Producto producto = new Producto();
            producto.setCodigoProducto(campos[0]);
            producto.setNombre(campos[1]);
            producto.setStock(Integer.parseInt(campos[2]));
            producto.setPrecio(Double.parseDouble(campos[3].replace(".", "")));
            producto.setRutProveedor(campos[4]);
            producto.setMailProveedor(campos[5]);
            productoService.crearProducto(producto);
            return ResponseEntity.ok("Producto verificado con éxito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

