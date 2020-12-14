package myclasses;

import java.time.LocalDate;
import java.util.Objects;

public class Magister extends Students{
private int diplomNumber;

    public void setDiplomNumber(int diplomNumber) { this.diplomNumber = diplomNumber; }

    public int getDiplomNumber() { return diplomNumber; }

    public Magister(String lName, String fName, String patronymic, int yearOfBirth, int montOfBirth, int dayOfBirth, String adres, String faqult, int course, int group, int diplomNumber) {
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
        this.diplomNumber = diplomNumber;
    }
    public Magister() { this("","","",0, 0, 0, "","", 0, 0, 0); }

    @Override
    public String toString() {
        return  "ID: " + ID + "Номер диплома: " + diplomNumber + '\n' +
                "ФИО: " + lName + ' ' + fName + ' ' + patronymic + '\n' +
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
                Objects.equals(lName, student.lName) &&
                Objects.equals(fName, student.fName) &&
                Objects.equals(patronymic, student.patronymic) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(adres, student.adres) &&
                Objects.equals(faqult, student.faqult) &&
                Objects.equals(diplomNumber, student.diplomNumber);
    }
}
