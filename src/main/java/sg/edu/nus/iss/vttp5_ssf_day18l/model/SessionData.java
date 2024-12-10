package sg.edu.nus.iss.vttp5_ssf_day18l.model;

public class SessionData {
    
    private String fullName;

    private String dateOfBirth;

    public SessionData() {
    }

    public SessionData(String fullName, String dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return fullName + "," + dateOfBirth;
    }

    
    
}