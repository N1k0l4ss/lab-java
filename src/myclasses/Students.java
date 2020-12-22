package myclasses;

import java.io.Serializable;
import java.time.LocalDate;

abstract public class Students implements Serializable {
    protected static int counter = 0;
    protected int ID;
    protected String lastName;
    protected String firstName;
    protected String patronymic;
    protected LocalDate birthday;
    protected String adres;
    protected String faqult;
    protected int course;
    protected int group;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Students(){
        counter++;
        ID = counter; }

    public int getID() {
        return ID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getFaqult() {
        return faqult;
    }

    public void setFaqult(String faqult) {
        this.faqult = faqult;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    abstract public String toString();

    @Override
    abstract public boolean equals(Object o);

}


