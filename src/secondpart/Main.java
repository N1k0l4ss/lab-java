package secondpart;
// Студент гр. 2151 Белоножко Никита, 2 вариант
import java.util.Scanner;
import static java.lang.System.exit;


public class Main
{
    private static Main mn;
    private Scanner s;
    public static void main(String[] args)
    {
        mn = new Main();
        mn.run();
    }

    private void run()
    {
        mn.s = new Scanner(System.in);
        Student[] students = new Student[5];
        students[0] = new Student("Петренко", "Михаил", "Викторович", 2001, "ул. Янтарная", "КСУ", 3, 3151);
        students[1] = new Student("Иванов", "Иван", "Иванович", 2001, "ул. Виноградная", "КСУ", 3, 3151);
        students[2] = new Student("Антонов", "Валерий", "Васильевич", 2002, "ул. Адмиральская", "ФЭМ", 3, 3144);
        students[3] = new Student("Высоцкий", "Иван", "Васильевич", 2001, "ул. Советская", "КСУ", 3, 3151);
        students[4] = new Student("Руденко", "Василий", "Михайлович", 2003, "ул. Янтарная", "ФЭМ", 1, 1144);
        while (true)
            mn.menu(students);
    }

    private void menu(Student[] students)
    {
        System.out.println("1. Студенты, которые родились после указанного года");
        System.out.println("2. Список студентов группы");
        System.out.println("3. Список студентов факультета");
        System.out.println("0. Выход");
        int sel = mn.s.nextInt();
        while (true)
        {
//            if (sel == 1)
//            {
////                System.out.println("Ведите год: ");
////                int year = mn.s.nextInt();
////                Student[] res = mn.getByYear(students, year);
////                for (int i = 0; i < res.length; i++)
////                    System.out.println(res[i].toString());
////                System.out.println();
//                break;
//            }
            if (sel == 2)
            {
                System.out.println("Введите группу: ");
                int group = mn.s.nextInt();
                Student[] res = mn.getByGroup(students, group);
                for (int i = 0; i < res.length; i++)
                    System.out.println(res[i].toString());
                System.out.println();
                break;
            }
            if (sel == 3)
            {
                System.out.println("Введите название факультета: ");
                String faq = mn.s.next();
                Student[] res = mn.getByFaq(students, faq);
                for (int i = 0; i < res.length; i++)
                    System.out.println(res[i].toString());
                System.out.println();
                break;
            }
            if (sel == 0)
                exit(0);
            System.out.println("Неверный ввод, попробуйте еще раз!");
            break;
        }
    }

    private Student[] getByFaq(Student[] students, String faq)
    {
        int counter = 0, j = 0;
        for (int i = 0; i < students.length; i++)
            if (students[i].getFaqult().equals(faq.toUpperCase()))
                counter++;
        Student [] res = new Student[counter];
        for (int i = 0; i < students.length; i++)
            if (students[i].getFaqult().equals(faq.toUpperCase()))
            {
                res[j] = students[i];
                j++;
            }
        return res;
    }

    private Student[] getByGroup(Student[] students, int group)
    {
        int counter = 0, j = 0;
        for (int i = 0; i < students.length; i++)
            if (students[i].getGroup() == group)
                counter++;
        Student [] res = new Student[counter];
        for (int i = 0; i < students.length; i++)
            if (students[i].getGroup() == group)
            {
                res[j] = students[i];
                j++;
            }
        return res;
    }

    private void getByYear(Student[] students, int year)
    {
        for (int i = 0; i < students.length; i++)
            if (students[i].getYearOfBirth() > year)
                System.out.println(students[i].toString() + '\n');
    }

}
