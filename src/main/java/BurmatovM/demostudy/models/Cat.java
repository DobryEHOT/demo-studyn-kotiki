package BurmatovM.demostudy.models;

public class Cat {
    private int id;
    private String owner;

    public Cat(int id, String owner, String name, String descr, String imageString, int isBuy) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.descr = descr;
        this.imageString = imageString;
        this.isBuy = isBuy;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    private String descr;
    private String imageString;
    private int isBuy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public int isBuy() {
        return isBuy;
    }

    public void setBuy(int buy) {
        isBuy = buy;
    }

    public String toString()
    {
        return id + " " + owner + " " + descr + " " + isBuy;
    }
}