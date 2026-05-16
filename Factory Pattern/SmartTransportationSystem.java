import java.util.Scanner;

interface Vehicle {
    void startTrip();
    double calculateFare(double distance);
    void assignRoute(String route);
    void getVehicleInfo();
    String getVehicleType();
}

class Bus implements Vehicle {
    private final int capacity = 40;
    private final double farePerKm = 15;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Bus trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Bus assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle     : Bus");
        System.out.println("Capacity    : " + capacity + " passengers");
        System.out.println("Fare per KM : " + farePerKm + " BDT");
    }

    @Override
    public String getVehicleType() {
        return "Bus";
    }
}

class Taxi implements Vehicle {
    private final int capacity = 4;
    private final double farePerKm = 30;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Taxi trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Taxi assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle     : Taxi");
        System.out.println("Capacity    : " + capacity + " passengers");
        System.out.println("Fare per KM : " + farePerKm + " BDT");
    }

    @Override
    public String getVehicleType() {
        return "Taxi";
    }
}

class MotorcycleDelivery implements Vehicle {
    private final int capacity = 1;
    private final double farePerKm = 10;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Motorcycle delivery started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Motorcycle assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle     : Motorcycle Delivery");
        System.out.println("Capacity    : " + capacity + " package");
        System.out.println("Fare per KM : " + farePerKm + " BDT");
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle Delivery";
    }
}

class ElectricScooter implements Vehicle {
    private final int capacity = 1;
    private final double farePerKm = 8;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Electric scooter trip started.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Scooter assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle     : Electric Scooter");
        System.out.println("Capacity    : " + capacity + " rider");
        System.out.println("Fare per KM : " + farePerKm + " BDT");
    }

    @Override
    public String getVehicleType() {
        return "Electric Scooter";
    }
}

abstract class VehicleFactory {

    public abstract Vehicle createVehicle();

    public Vehicle orderVehicle(String route) {
        Vehicle vehicle = createVehicle();
        System.out.println("\n[Factory] Creating a " + vehicle.getVehicleType() + "...");
        vehicle.assignRoute(route);
        return vehicle;
    }
}

class BusFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bus();
    }
}

class TaxiFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Taxi();
    }
}

class MotorcycleDeliveryFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new MotorcycleDelivery();
    }
}

class ElectricScooterFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new ElectricScooter();
    }
}

public class SmartTransportationSystem {

    public static VehicleFactory getFactory(int choice) {
        switch (choice) {
            case 1: return new BusFactory();
            case 2: return new TaxiFactory();
            case 3: return new MotorcycleDeliveryFactory();
            case 4: return new ElectricScooterFactory();
            default: return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  SMART TRANSPORTATION MANAGEMENT SYSTEM  ║");
        System.out.println("║       (Factory Method Design Pattern)     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean keepRunning = true;

        while (keepRunning) {

            System.out.println("\n==========================================");
            System.out.println("  Select a Vehicle Type:");
            System.out.println("  1. Bus");
            System.out.println("  2. Taxi");
            System.out.println("  3. Motorcycle Delivery");
            System.out.println("  4. Electric Scooter");
            System.out.println("  0. Exit");
            System.out.println("==========================================");
            System.out.print("  Enter your choice: ");

            int vehicleChoice = scanner.nextInt();
            scanner.nextLine();

            if (vehicleChoice == 0) {
                System.out.println("\nGoodbye! Thank you for using the system.");
                break;
            }

            VehicleFactory factory = getFactory(vehicleChoice);

            if (factory == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.print("  Enter route (e.g., Dhaka to Chittagong): ");
            String route = scanner.nextLine();

            Vehicle myVehicle = factory.orderVehicle(route);

            System.out.println("\n--- Vehicle Information ---");
            myVehicle.getVehicleInfo();

            System.out.print("\nEnter distance in KM: ");
            double distance = scanner.nextDouble();
            scanner.nextLine();

            double fare = myVehicle.calculateFare(distance);

            System.out.println("\n--- Trip Details ---");
            myVehicle.startTrip();
            System.out.printf("Total Fare for %.1f KM : %.2f BDT%n", distance, fare);

            System.out.print("\nBook another vehicle? (yes/no): ");
            String again = scanner.nextLine().trim().toLowerCase();
            keepRunning = again.equals("yes") || again.equals("y");
        }

        scanner.close();
    }
}
