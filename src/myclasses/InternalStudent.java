package myclasses;

import java.time.LocalDate;
import java.util.Objects;

public class InternalStudent extends Students {
    private static int counter = 0;
    private int ID;
    private String lName;
    private String fName;
    private String patronymic;
    private LocalDate birthday;
    private String adres;
    private String faqult;
    private int course;
    private int group;

    public InternalStudent(String lName, String fName, String patronymic, int yearOfBirth, int montOfBirth, int dayOfBirth, String adres, String faqult, int course, int group) {
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
    public InternalStudent() { this("","","",0, 0, 0, "","",0,0); }

    public String toString()
    {
        return  "ID: " + ID + '\n' +
                "ФИО: " + lName + ' ' + fName + ' ' + patronymic + '\n' +
                "Дата рождения: " + birthday.toString() + '\n' +
                "Адрес: " + adres + ".\tФакультет: " + faqult + '\n' +
                "Группа: " + group + "\tКурс: " + course;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalStudent student = (InternalStudent) o;
        return course == student.course &&
                group == student.group &&
                Objects.equals(lName, student.lName) &&
                Objects.equals(fName, student.fName) &&
                Objects.equals(patronymic, student.patronymic) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(adres, student.adres) &&
                Objects.equals(faqult, student.faqult);
    }
}
