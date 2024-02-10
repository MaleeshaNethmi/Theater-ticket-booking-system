public class Person {
    private String name;  // declare a private String variable for the person's name
    private String surname;  // declare a private String variable for the person's surname
    private String email;  // declare a private String variable for the person's email

    public Person(String name, String surname, String email) {    // constructor for creating a new Person object with a given name, surname, and email
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {   // method for getting the person's name
        return name;
    }

    public String getSurname() {  // method for getting the person's surname
        return surname;
    }

    public String getEmail() {  // method for getting the person's email
        return email;
    }

    public void setName(String name) {   // method for setting the person's name
        this.name = name;
    }

    public void setSurname(String surname) {  // method for setting the person's surname
        this.surname = surname;
    }

    public void setEmail(String email) {  // method for setting the person's email
        this.email = email;
    }

    // method for returning a string representation of the Person object
    public String toString() {
        return "Person [name=" + name + ", surname=" + surname + ", email=" + email + "]";
    }

}