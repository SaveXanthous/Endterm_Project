package org.controller;

import org.controller.abstract_classes.AbstractCrudController;
import org.models.VendingMachine;
import org.rest_service.VendingMachineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vending_machines")
public class VendingMachineController
        extends AbstractCrudController<VendingMachine, Integer> {

    private final VendingMachineService service;

    public VendingMachineController(
            VendingMachineService service
    ) {
        this.service = service;
    }

    @Override
    protected VendingMachineService getService() {
        return service;
    }
}
