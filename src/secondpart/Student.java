package secondpart;
// Студент гр. 2151 Белоножко Никита, 2 вариант
public class Student
{
    private static int counter = 0;
    private int ID;
    private String lName;
    private String fName;
    private String patronymic;
    private int yearOfBirth;
    private String adres;
    private String faqult;
    private int course;
    private int group;

    public Student(String lName, String fName, String patronymic, int yearOfBirth, String adres, String faqult, int course, int group) {
        counter++;
        ID = counter;
        this.fName = fName;
        this.lName = lName;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.adres = adres;
        this.faqult = faqult;
        this.course = course;
        this.group = group;
    }

    public Student() {
        this("","","",0,"","",0,0);
    }

    public String toString() {
        return  "ID: " + ID + '\n' +
                "ФИО: " + lName + ' ' + fName + ' ' + patronymic + '\n' +
                "Год рождения: " + yearOfBirth + '\n' +
                "Адрес: " + adres + ".\tФакультет: " + faqult + '\n' +
                "Группа: " + group + "\tКурс: " + course;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
}
