package myclasses;

import java.time.LocalDate;
import java.util.Objects;

abstract public class Students {
    protected static int counter = 0;
    protected int ID;
    protected String lName;
    protected String fName;
    protected String patronymic;
    protected LocalDate birthday;
    protected String adres;
    protected String faqult;
    protected int course;
    protected int group;

    public Students() { this("","","",0, 0, 0, "","",0,0); }
    public String getfName() { return fName; }
    public void setfName(String fName) { this.fName = fName; }
    public String getlName() { return lName; }
    public void setlName(String lName) { this.lName = lName; }
    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(int year, int month, int day) { this.birthday = LocalDate.of(year, month, day); }
    public String getAdres() { return adres; }
    public void setAdres(String adres) { this.adres = adres; }
    public String getFaqult() { return faqult; }
    public void setFaqult(String faqult) { this.faqult = faqult; }
    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }
    public int getGroup() { return group; }
    public void setGroup(int group) { this.group = group; }

    public Students(String lName, String fName, String patronymic, int yearOfBirth, int montOfBirth, int dayOfBirth, String adres, String faqult, int course, int group) {
        counter++;
        ID = counter;
        this.fName = fName;
        this.lName = lName;
        this.patronymic = patronymic;
        this.birthday = LocalDate.of(yearOfBirth, montOfBirth, dayOfBirth);
        this.adres = adres;
        this.faqult = faqult;
        this.course = course;
        this.group = group;
    }

    abstract public String toString();

    @Override
    abstract public boolean equals(Object o);

}


