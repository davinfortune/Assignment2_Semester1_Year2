package ApplicationModels;

public class propertyAdmin {
    private String name;
    private String adminId;
    private String username;
    private String password;
    private String masterAdminPassword = "root";

    public propertyAdmin(String username, String password, String adminId, String name) {
        this.name = name;
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

    @Override
    public String toString() {
        return "propertyAdmin{" +
                "name='" + name + '\'' +
                ", adminId='" + adminId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
