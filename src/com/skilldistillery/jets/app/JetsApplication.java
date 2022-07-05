package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entities.*;

public class JetsApplication {
    // TODO: addPilot
    // TODO: Clean up decimal price output
    // TODO: ASCII display per menu
    // TODO: Display Mach speed
    // TODO: AirField...

    private List<Jets> fleet = new ArrayList<Jets>();
    private AirField airField = new AirField(fleet);
    private Scanner kb;

    public static void main(String[] args) {
        JetsApplication jetApp = new JetsApplication();
        jetApp.run();
    }

    private void run() {
        fleet = parseJets("jets.csv");

        kb = new Scanner(System.in);

        Integer menuChoice = 0;
        while (!(menuChoice.equals(9))) {
            double amount = 0;
            Jets winningJet = null;

            mainMenu();
            switch (menuChoice = stringToInt(kb.next())) {
            case 1:
                printHeader("Fleet of " + fleet.size() + " Jets", 60);
                airField.displayFleet();
                break;
            case 2:
                printHeader("Fleet has been Scrambled", 60);
                for (Jets jet : fleet) {
                    jet.fly();
                }
                break;
            case 3:
                printHeader("Fastest Jet in the Fleet", 60);
                amount = 0;
                winningJet = null;
                for (Jets jet : fleet) {
                    if (jet.getSpeed() > amount) {
                        amount = jet.getSpeed();
                        winningJet = jet;
                    }
                }
                System.out.println(winningJet.toString());
                break;
            case 4:
                printHeader("Longest Ranging Jet in the Fleet", 60);
                amount = 0;
                winningJet = null;
                for (Jets jet : fleet) {
                    if (jet.getRange() > amount) {
                        amount = jet.getRange();
                        winningJet = jet;
                    }
                }
                System.out.println(winningJet.toString());
                break;
            case 5:
                printHeader("Beginning Attack Runs", 60);
                amount = 0;
                winningJet = null;
                for (Jets jet : fleet) {
                    if (jet instanceof CombatJet) {
                        ((CombatJet) jet).fireMissiles();
                        ((CombatJet) jet).releaseBombs();
                    }
                }
                break;
            case 6:
                printHeader("Performing Cargo Training Exercises", 60);
                amount = 0;
                winningJet = null;
                for (Jets jet : fleet) {
                    if (jet instanceof CargoJet) {
                        ((CargoJet) jet).loadCargo();
                        ((CargoJet) jet).unloadCargo();
                    }
                    if (jet instanceof PrivateJet) {
                        ((PrivateJet) jet).loadCargo();
                        ((PrivateJet) jet).unloadCargo();
                    }
                }
                break;
            case 7:
                Jets jet;
                String type, model;
                double speed, price;
                int range;

                printHeader("Enter New Jet Information:", 60);
                System.out.println("Enter Jet Type");
                type = kb.next();
                System.out.println("Enter Jet Model");
                model = kb.next();
                System.out.println("Enter Jet Speed");
                speed = stringToInt(kb.next());
                System.out.println("Enter Jet Range");
                range = stringToInt(kb.next());
                System.out.println("Enter Jet Price");
                price = stringToInt(kb.next());

                jet = new VanillaJet(model, speed, range, price);

                fleet.add(jet);

                printHeader("Updated Fleet List", 60);
                airField.displayFleet();

                break;
            case 8:
                printHeader("Select the Jet to be Removed:", 60);
                airField.displayFleet();
                menuChoice = stringToInt(kb.next());
                fleet.remove(menuChoice - 1);
                printHeader("Updated Fleet List:", 60);
                airField.displayFleet();
                break;
            case 9:
                System.out.println("Fly Safe!");
                break;
            default:
                System.err.println("Invalid Choice");
            }
        }
        kb.close();
    }

    private void printHeader(String str, int length) {
        String header = "";
        str = "Skill Distillery Fleet: " + str;

        for (int i = 0; i < length; i++) {
            header += "-";
        }
        System.out.println(header);
        System.out.println(str);
        System.out.println(header);
    }

    private List<Jets> parseJets(String fileName) {
        Jets jet;
        String type, model;
        double speed, price;
        int range;

        try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufIn.readLine()) != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    String[] fields = line.split(",");
                    jet = null;
                    type = fields[0];
                    model = fields[1];
                    speed = Double.parseDouble(fields[2]);
                    range = Integer.parseInt(fields[3]);
                    price = Double.parseDouble(fields[4]);

                    switch (type) {
                    case "VanillaJet":
                        jet = new VanillaJet(model, speed, range, price);
                        break;
                    case "CommercialJet":
                        jet = new CommercialJet(model, speed, range, price);
                        break;
                    case "CombatJet":
                        jet = new CombatJet(model, speed, range, price);
                        break;
                    case "CargoJet":
                        jet = new CargoJet(model, speed, range, price);
                        break;
                    case "PrivateJet":
                        jet = new PrivateJet(model, speed, range, price);
                        break;
                    default:
                        System.out.println("INVALID JET TYPE");
                    }
                    fleet.add(jet);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return fleet;
    }

    private void mainMenu() {
        System.out.println("--------------------------------------------\n"
                + " Skill Distillery Jet Fleet Management Menu \n"
                + "--------------------------------------------\n" + " 1. List Entire Fleet\n"
                + " 2. Scramble All Jets\n" + " 3. Display Fastest Jet\n"
                + " 4. Display Jet with Longest Range\n" + " 5. Attack Simulation\n"
                + " 6. Cargo Exercises\n" + " 7. Add a Jet to Fleet\n"
                + " 8. Remove a Jet from Fleet\n" + " 9. Quit\n"
                + "--------------------------------------------\n" + "Choice:\n");
    }

    public int stringToInt(String str) {
        int intFromString = 0;

        if (str.matches("^[\\d]+$")) {
            intFromString = Integer.parseInt(str);
        }
        return intFromString;
    }
}
