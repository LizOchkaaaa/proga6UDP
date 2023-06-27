package org.example.models;

import org.example.annotation.Complex;
import org.example.xmlUtils.DateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.ZonedDateTime;

@XmlRootElement(name="person")
public class Person implements Serializable {
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private ZonedDateTime birthday; //Поле не может быть null
    @XmlElement
    private Integer weight; //Поле может быть null, Значение поля должно быть больше 0
    @XmlElement
    private String passportID; //Поле не может быть null
    @XmlElement
    @Complex
    private Color hairColor; //Поле не может быть null

    public Person(){}
    public Person(String name , ZonedDateTime birthday , Integer weight , String passportID , Color hairColor) {
        this.name = name;
        this.weight = weight;
        this.birthday = birthday;
        this.passportID = passportID;
        this.hairColor = hairColor;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public String getPassportID() {
        return passportID;
    }

    public int compareTo(Person o) {
        if (o.getName().compareTo(o.getName()) != 0 ) return o.getName().compareTo(o.getName());
        if (o.getWeight() - this.weight != 0) return o.getWeight() - this.weight;
        if (o.getPassportID().compareTo(o.getPassportID()) != 0 ) return o.getPassportID().compareTo(o.getPassportID());
        return 0;
    }
    
    @Override
    public String toString() {
        return "Person [name=" + name + ", birthday=" + birthday + ", weight="
                + weight + ", passportID=" + passportID + ", hairColor=" + hairColor
                + "]";
    }
}
