package bdbt_bada_project.SpringApplication;

import java.time.LocalDate;

public class Contract {
    private int id;
    private String sponsorName;
    private LocalDate dateOfExpiry;
    private int monetaryValue;
    private String industry;
    private boolean isActive;
    private String phoneNumber;
    private String email;

    public Contract(int id, String sponsorName, LocalDate dateOfExpiry, int monetaryValue, String industry, boolean isActive, String phoneNumber, String email) {
        super();
        this.id = id;
        this.sponsorName = sponsorName;
        this.dateOfExpiry = dateOfExpiry;
        this.monetaryValue = monetaryValue;
        this.industry = industry;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contract() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public int getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(int monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", sponsorName='" + sponsorName + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", monetaryValue=" + monetaryValue +
                ", industry='" + industry + '\'' +
                ", isActive=" + isActive +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
