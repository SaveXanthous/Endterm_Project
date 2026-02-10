package org.controller;

import org.controller.abstract_classes.AbstractCrudController;
import org.models.Item;
import org.rest_service.ItemService;
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
