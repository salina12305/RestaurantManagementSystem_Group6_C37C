
package Model;

public class User {
    private int id;
    private String company_name;
    private String email;
    private String password;
    private String confirm_password;
    private String role; 

    public User() {
     
    }

    public User(String company_name, String email, String password, String confirm_password, String role) {
        this.company_name = company_name;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
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

    public String getConfirmPassword() {
        return confirm_password;
    }

    public void setConfirmPassword(String confirm_password) {
        this.confirm_password = confirm_password;
    }
     public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
