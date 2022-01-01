package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/test")
    private String test(){
        return "Test";
    }

    @GetMapping("/{skuCode}")
    public Boolean isStockAvailable(@PathVariable String skuCode){
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                                    .orElseThrow(() -> new RuntimeException("Cannot find inventory for "+skuCode));

        return inventory.getStock()>0;
    }
}
