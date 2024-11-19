public class Customer {
    private int id;
    private String surname;
    private String name;
    private String fathername;
    private String address;
    private long phoneNumber;
    private long cardNumber;
    private double bonusBalance;

    public Customer(int id, String surname, String name, String fathername, String address, long phoneNumber, long cardNumber, double bonusBalance){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.fathername=fathername;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.cardNumber=cardNumber;
        this.bonusBalance=bonusBalance;
    }
    public Customer(int id, String surname, String name, String fathername, String address, long phoneNumber, long cardNumber){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.fathername=fathername;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.cardNumber=cardNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setBonusBalance(double bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFathername() {
        return fathername;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public double getBonusBalance() {
        return bonusBalance;
    }

    public String toString(){
        return "Customer{id: " + id
                + " surname: " + surname
                + " name: " + name
                + " fathername: " + fathername
                + " address: " + address
                + " phone: " + phoneNumber
                + " card: " + cardNumber
                + " bonusbalance : " + bonusBalance
                +  "}";
    }
}