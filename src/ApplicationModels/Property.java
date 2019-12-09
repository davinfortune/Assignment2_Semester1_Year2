package ApplicationModels;

public class Property {
    private int propertyId;
    private String description;
    private String address;
    private String category;
    private String locationGeneral;
    private String locationSpecific;
    private String BER;
    private String eircode;
    private double price;
    private String imagePath;

    public Property(int propertyId, String description, String address, String category, String locationGeneral, String locationSpecific, String BER, String eircode, double price, String imagePath) {
        this.propertyId = propertyId;
        this.description = description;
        this.address = address;
        this.category = category;
        this.locationGeneral = locationGeneral;
        this.locationSpecific = locationSpecific;
        this.BER = BER;
        this.eircode = eircode;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocationGeneral() {
        return locationGeneral;
    }

    public void setLocationGeneral(String locationGeneral) {
        this.locationGeneral = locationGeneral;
    }

    public String getLocationSpecific() {
        return locationSpecific;
    }

    public void setLocationSpecific(String locationSpecific) {
        this.locationSpecific = locationSpecific;
    }

    public String getBER() {
        return BER;
    }

    public void setBER(String BER) {
        this.BER = BER;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "\n PropertyId : " + propertyId  +
                "\n\n Description : " + description +
                "\n\n Address : " + address +
                "\n\n Town : " + locationSpecific +
                "\n\n County : " + locationGeneral +
                "\n\n Eircode : " + eircode +
                "\n\n Property Type : " + category +
                "\n\n BER : " + BER +
                "\n\n Price : €" + price + "\n";
    }
}
