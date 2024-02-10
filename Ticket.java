public class Ticket {
    private int row;  // declare a private int variable for the row number
    private int seat;  // declare a private int variable for the seat number
    private double price;  // declare a private double variable for the ticket price
    private Person person;  // declare a private Person object variable for the ticket owner

    public Ticket(int row, int seat, double price, Person person) {  // constructor for creating a new Ticket object with a given row, seat, price, and person
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // getters and setters for the attributes
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void print() {      // method for printing the ticket information
        System.out.println("..............................................");
        System.out.println("Name     : " + person.getName());
        System.out.println("Surname  : " + person.getSurname());
        System.out.println("Email    : " + person.getEmail());
        System.out.println("Row No   : " + row);
        System.out.println("Seat No  : " + seat);
        System.out.println("Price    : $" + price);
    }

}