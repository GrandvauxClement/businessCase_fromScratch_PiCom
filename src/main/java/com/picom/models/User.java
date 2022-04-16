package com.picom.models;

public class User {

    private Long id;

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String phoneNumber;

    private boolean isVerified;

    private String numSiret;

    private String companyName;

    private String roadName;

    private String postalCode;

    private City city;

    private Role role;

    // constructor when register without id & default role to user
    public User(String lastName, String firstName, String email, String password, String phoneNumber, String numSiret,
                String companyName, String roadName, String postalCode, City city) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.numSiret = numSiret;
        this.companyName = companyName;
        this.roadName = roadName;
        this.postalCode = postalCode;
        this.city = city;
        this.role = new Role(2L, "user");
    }

    // constructor when get user to Db with all informations
    public User(Long id, String lastName, String firstName, String email, String password, String phoneNumber,
                boolean isVerified, String numSiret, String companyName, String roadName, String postalCode,
                City city, Role role) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
        this.numSiret = numSiret;
        this.companyName = companyName;
        this.roadName = roadName;
        this.postalCode = postalCode;
        this.city = city;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(String numSiret) {
        this.numSiret = numSiret;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
