package org.example.controller;

import org.example.controller.abstract_classes.AbstractCrudController;
import org.example.models.Item;
import org.example.service.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController extends AbstractCrudController<Item, Integer> {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @Override
    protected ItemService getService() {
        return service;
    }
}
