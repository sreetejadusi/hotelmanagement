import java.util.*;


class Hotel {
    private Room[] rooms;

    public Hotel(String hotelName, Room[] rooms) {
        this.rooms = rooms;
    }

    
    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                System.out.println(room);
            }
        }
    }

    public void displayOccupiedRooms() {
        System.out.println("Occupied Rooms:");
        for (Room room : rooms) {
            if (room.isOccupied()) {
                System.out.println(room.getRoomNumber() + " : " + room.getGuestName());
            }
        }
    }

    
    public void checkIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        
        Room room = findRoom(roomNumber);
        if (room != null) {
            if (room.isOccupied()) {
                System.out.println("Room " + roomNumber + " is already occupied.");
            } else {
                System.out.print("Enter guest name: ");
                String guestName = scanner.next();
                room.setGuestName(guestName);
                room.setOccupied(true);
                System.out.println("Guest " + guestName + " checked into room " + roomNumber + ".");
            }
        } else {
            System.out.println("Room " + roomNumber + " does not exist.");
        }
    }

    
    public void checkOut() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        
        Room room = findRoom(roomNumber);
        if (room != null) {
            if (room.isOccupied()) {
                String guestName = room.getGuestName();
                room.setGuestName("");
                room.setOccupied(false);
                System.out.println("Guest " + guestName + " checked out of room " + roomNumber + ".");
            } else {
                System.out.println("Room " + roomNumber + " is not occupied.");
            }
        } else {
            System.out.println("Room " + roomNumber + " does not exist.");
        }
    }

    
    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}


class Room {
    private int roomNumber;
    private String guestName;
    private boolean occupied;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.guestName = "";
        this.occupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + ": " + (occupied ? "Occupied by " + guestName : "Available");
    }
}


public class HotelManagementSystem {
    public static void main(String[] args) {
        
        Room[] rooms = {
                new Room(101),
                new Room(102),
                new Room(103),
                new Room(201),
                new Room(202),
                new Room(203),
        };
        
        Hotel hotel = new Hotel("Grand Hotel", rooms);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Check-in");
            System.out.println("2. Check-out");
            System.out.println("3. View available rooms");
            System.out.println("4. View occupied rooms");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    hotel.checkIn();
                    break;
                case 2:
                    hotel.checkOut();
                    break;
                case 3:
                    hotel.displayAvailableRooms();
                    break;
                case 4:
                    hotel.displayOccupiedRooms();
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}