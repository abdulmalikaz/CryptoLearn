public class Profile {
    private String userId;
    private String address;
    private String phoneNumber;
    private String email;
    private String occupation;

    public Profile(String userId, String address, String phoneNumber, String email, String occupation) {
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.occupation = occupation;
    }
    // Getters
    public String getUserId() { return userId; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getOccupation() { return occupation; }
}