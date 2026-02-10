package org.service;

import org.repository.CellRepository;
import org.repository.ItemRepository;
import org.repository.VendingMachineRepository;
import org.utils.DatabaseConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Service {
    void Service(){}

    public static void init(){
        DatabaseConnection.set_URL("jdbc:postgresql://localhost:5432/vending_machine");
        DatabaseConnection.set_USER("postgres");
        DatabaseConnection.set_PASSWORD("almanax1209");

        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connection = db.getConnection();

            VendingMachineRepository vendingMachineRepository = new VendingMachineRepository(connection);
            CellRepository cellRepository = new CellRepository(connection);
            ItemRepository itemRepository = new ItemRepository(connection);

            VendingService service = new VendingService(
                    vendingMachineRepository,
                    cellRepository,
                    itemRepository
            );

            VendingCommandHandler handler = new VendingCommandHandler(service);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Started. Type command or exit.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) break;
                if (input.isEmpty()) continue;

                System.out.println(handler.handle(input));
            }

            scanner.close();

        } catch(Exception e){
            System.out.println("Ошибка запуска: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
