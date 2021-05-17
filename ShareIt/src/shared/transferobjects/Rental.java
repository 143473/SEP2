package shared.transferobjects;

public class Rental {
    private int id;
    private String name;
    private String description;
    private int price;
    private String otherInformation;
    private String stateName;
    private Member member;

    public Rental(int id, String name, String description, int price, String otherInformation, String stateName, Member member) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.otherInformation = otherInformation;
        this.stateName = stateName;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}