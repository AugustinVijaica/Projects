package Game.Shop.model;

public class ClientAccount {

    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private String address;
    private String likedGenre1;
    private String likedGenre2;

    public ClientAccount() {

    }

    public ClientAccount(String username, String password, String firstName, String lastName, String email,
                         String phoneNumber, String country, String address, String likedGenre1, String likedGenre2)
    {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.address = address;
        this.likedGenre1 = likedGenre1;
        this.likedGenre2 = likedGenre2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLikedGenre1() {
        return likedGenre1;
    }

    public void setLikedGenre1(String likedGenre1) {
        this.likedGenre1 = likedGenre1;
    }

    public String getLikedGenre2() {
        return likedGenre2;
    }

    public void setLikedGenre2(String likedGenre2) {
        this.likedGenre2 = likedGenre2;
    }
}
