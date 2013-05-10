package ch.bfh.bti7081.s2013.yellow.model.person;


import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * This entity represents a basic person. Each person must inherit from this abstract class.
 * @param <T> Inherited person class
 */
@Entity
public abstract class Person<T> extends YellowEntity<T>{

    public Person(final Class<T> c) {
        super(c);
    }


    @Basic(optional = false)
    @Column(length = 60)
    String name;

    @Basic(optional = false)
    @Column(length = 60)
    String vorname;

    @Basic(optional = true)
    Date birthday;

    @Basic(optional = false)
    @Column(length = 40)
    String phoneNr;

//    Address address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}
