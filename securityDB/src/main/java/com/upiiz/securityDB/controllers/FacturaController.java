package com.upiiz.securityDB.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @GetMapping
    public String listarFacturas(){
        return "Creando Facturas";
    }

    @PutMapping
    public String actualizarFacturas(){
        return "Actualizando facturas(";

    }

    @DeleteMapping
    public String eliminarFactura(){
        return "Eliminando factura";
    }
}
