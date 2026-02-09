package org.example.controller;

import org.example.controller.abstract_classes.AbstractCrudController;
import org.example.models.Cell;
import org.example.service.CellService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cells")
public class CellController
        extends AbstractCrudController<Cell, Integer> {

    private final CellService service;

    public CellController(CellService service) {
        this.service = service;
    }

    @Override
    protected CellService getService() {
        return service;
    }
}
