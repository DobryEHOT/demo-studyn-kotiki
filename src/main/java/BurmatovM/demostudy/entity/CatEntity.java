package BurmatovM.demostudy.entity;

import java.awt.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@jakarta.persistence.Entity
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idImage;
    private Long idOwner;
    private String owner;

    public CatEntity(){
    }
    public CatEntity(Long idImage, String owner, String name, String descr, String imageString, int isBuy) {
        this.idImage = idImage;
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


    private String descr;
    private String imageString;
    private int isBuy;

    public String toString()
    {
        return id + " " + owner + " " + descr + " " + isBuy;
    }
}
