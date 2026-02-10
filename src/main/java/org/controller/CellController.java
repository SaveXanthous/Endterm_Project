package org.controller;

import org.controller.abstract_classes.AbstractCrudController;
import org.models.Cell;
import org.rest_service.CellService;
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
