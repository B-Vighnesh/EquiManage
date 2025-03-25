package model;

public class Equipments {
    private int id;
    private String name;
    private String type;
    private boolean isAvailable;
    private int buyerId;

    public Equipments(int id, String name, String type, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isAvailable = isAvailable;

    }

    public Equipments() {}

    @Override
    public String toString() {
        return "Equipments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +

                '}';
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public int getBuyerId() { return buyerId; }
    public void setBuyerId(int buyerId) { this.buyerId = buyerId; }


}
