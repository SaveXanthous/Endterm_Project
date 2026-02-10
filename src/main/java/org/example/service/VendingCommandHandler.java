package org.example.service;

import org.example.exceptions.DatabaseOperationException;

public class VendingCommandHandler {

    private final VendingServiceInterface service;

    public VendingCommandHandler(VendingServiceInterface service) {
        this.service = service;
    }

    public String handle(String input) throws DatabaseOperationException {
        if (input == null) return "empty input";

        String[] parts = CommandParser.split(input);
        String command = parts[0];

        if (command.equals("info")) {
            return """
                * * info input
                * select - input: select (vending_machine, cells, items)
                * buy - input: buy {vending_machine_id} {cell_id} {quantity}
                * insert vending machine - input: insert_vending_machine {id} {name} {place} {max_cells} {max_quantity}
                * insert cells - input: insert_cell {id} {vending_machine_id} {item_id} {quantity}
                * insert items - input: insert_item {id} {name} {price}
                * update vending machine - input: update_vending_machine {id} {name} {place} {max_cells} {max_quantity}
                * update cell - input: update_cell {id} {vending_machine_id} {item_id} {quantity}
                * update item - input: update_item {id} {name} {price}
                * delete vending machine - input: delete_vending_machine {id}
                * delete cell - input: delete_cell {id}
                * delete item - input: delete_item {id}
                """;
        }

        // select
        if (command.equals("select")) {
            if (parts.length != 2) return "input: select (vending_machine, cells, items)";
            return service.select(parts[1]);
        }

        // buy
        if (command.equals("buy")) {
            if (parts.length != 4) return "input: buy {vending_machine_id} {cell_id} {quantity}";
            return service.buy(
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3])
            );
        }

        // insert
        if (command.equals("insert_vending_machine")) {
            if (parts.length != 5)
                return "input: insert_vending_machine {name} {place} {max_cells} {max_quantity}";
            return service.insertVendingMachine(
                    parts[1],
                    parts[2],
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4])
            );
        }

        if (command.equals("insert_cell")) {
            if (parts.length != 4)
                return "input: insert_cell {vending_machine_id} {item_id} {quantity}";
            return service.insertCell(
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3])
            );
        }

        if (command.equals("insert_item")) {
            if (parts.length != 3)
                return "input: insert_item {name} {price}";
            return service.insertItem(
                    parts[1],
                    Integer.parseInt(parts[2])
            );
        }


        // update
        if (command.equals("update_vending_machine")) {
            if (parts.length != 6)
                return "input: update_vending_machine {id} {name} {place} {max_cells} {max_quantity}";
            return service.updateVendingMachine(
                    Integer.parseInt(parts[1]),
                    parts[2],
                    parts[3],
                    Integer.parseInt(parts[4]),
                    Integer.parseInt(parts[5])
            );
        }

        if (command.equals("update_cell")) {
            if (parts.length != 5)
                return "input: update_cell {id} {vending_machine_id} {item_id} {quantity}";
            return service.updateCell(
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4])
            );
        }

        if (command.equals("update_item")) {
            if (parts.length != 4)
                return "input: update_item {id} {name} {price}";
            return service.updateItem(
                    Integer.parseInt(parts[1]),
                    parts[2],
                    Integer.parseInt(parts[3])
            );
        }

        // delete
        if (command.equals("delete_vending_machine")) {
            if (parts.length != 2) return "input: delete_vending_machine {id}";
            return service.deleteVendingMachine(Integer.parseInt(parts[1]));
        }

        if (command.equals("delete_cell")) {
            if (parts.length != 2) return "input: delete_cell {id}";
            return service.deleteCell(Integer.parseInt(parts[1]));
        }

        if (command.equals("delete_item")) {
            if (parts.length != 2) return "input: delete_item {id}";
            return service.deleteItem(Integer.parseInt(parts[1]));
        }

        return "Unknown command: " + command;
    }
}
