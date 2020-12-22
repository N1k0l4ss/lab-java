package myclasses;

import java.time.LocalDate;
import java.util.Objects;

public class Magister extends Students{
private int diplomNumber;

    public void setDiplomNumber(int diplomNumber) { this.diplomNumber = diplomNumber; }

    public int getDiplomNumber() { return diplomNumber; }

    public Magister(String lName, String fName, String patronymic, LocalDate date, String adres, String faqult, int course, int group, int diplomNumber) {
        ID = Students.counter;
        this.firstName = fName;
        this.lastName = lName;
        this.patronymic = patronymic;
        this.birthday = date;
        this.adres = adres;
        this.faqult = faqult;
        this.course = course;
        this.group = group;
        this.diplomNumber = diplomNumber;
    }
    public Magister() { this("","","",LocalDate.of(0,0,0), "","", 0, 0, 0); }

    @Override
    public String toString() {
        return  "ID: " + ID + "\tНомер диплома: " + diplomNumber + '\n' +
                "ФИО: " + lastName + ' ' + firstName + ' ' + patronymic + '\n' +
                "Дата рождения: " + birthday.toString() + '\n' +
                "Адрес: " + adres + ".\tФакультет: " + faqult + '\n' +
                "Группа: " + group + "\tКурс: " + course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magister student = (Magister) o;
        return course == student.course &&
                group == student.group &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(patronymic, student.patronymic) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(adres, student.adres) &&
                Objects.equals(faqult, student.faqult) &&
                Objects.equals(diplomNumber, student.diplomNumber);
    }
}
