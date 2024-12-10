package sg.edu.nus.iss.vttp5_ssf_day18l.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {
    
    private Integer id; // note: validation not needed for id to avoid errors
    
    @NotEmpty(message= "Full name is required")
    @Size(min = 5, max = 15, message = "Full name must be between 5 to 15 characters")
    private String fullName;

    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Phone number must begin with 8 or 9, followed by 7 digits")
    private String phone;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;


    public Person() {
    }

    public Person(Integer id, String fullName, String email, String phone, Date dob) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    // this also works but not recommended as id should be first since its a unique identifier.
    // public Person(Date dob, String email, String fullName, Integer id, String phone) {
    //     this.dob = dob;
    //     this.email = email;
    //     this.fullName = fullName;
    //     this.id = id;
    //     this.phone = phone;
    // }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    

    
}
