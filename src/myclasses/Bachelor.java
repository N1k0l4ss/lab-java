package myclasses;

import java.time.LocalDate;
import java.util.Objects;

public class Bachelor extends Students {

    public Bachelor(String lName, String fName, String patronymic, LocalDate date, String adres, String faqult, int course, int group) {
        ID = Students.counter;
        this.firstName = fName;
        this.lastName = lName;
        this.patronymic = patronymic;
        this.birthday = date;
        this.adres = adres;
        this.faqult = faqult;
        this.course = course;
        this.group = group;
    }
    public Bachelor() { this("","","",LocalDate.of(0,0,0), "","", 0, 0); }

    @Override
    public String toString()
    {
        return  "ID: " + ID + '\n' +
                "ФИО: " + lastName + ' ' + firstName + ' ' + patronymic + '\n' +
                "Дата рождения: " + birthday.toString() + '\n' +
                "Адрес: " + adres + ".\tФакультет: " + faqult + '\n' +
                "Группа: " + group + "\tКурс: " + course;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bachelor student = (Bachelor) o;
        return course == student.course &&
                group == student.group &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(patronymic, student.patronymic) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(adres, student.adres) &&
                Objects.equals(faqult, student.faqult);
    }
}
