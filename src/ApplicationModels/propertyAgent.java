package ApplicationModels;

public class propertyAgent {
    private String username;
    private String password;
    private String agentId;
    private String location;
    private String name;
    private int phoneNumber;

    public propertyAgent(String username, String password, String agentId, String location, String name, int phoneNumber) {
        this.username = username;
        this.password = password;
        this.agentId = agentId;
        this.location = location;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return
                " Username : " + username + "\n\n" +
                        " Agent Id : " + agentId + "\n\n" +
                        " Location : " + location + "\n\n" +
                        " Name : " + name + "\n\n" +
                        " Phone Number : " + phoneNumber;
    }
}
