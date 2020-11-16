package firtpart;
// Студент гр. 2151 Белоножко Никита, 2 вариант
import java.util.Scanner;

public class Main
{
    private static Main mn;

    public static void main(String[] args)
    {
        mn = new Main();
        mn.run();
    }

    private void run()
    {
        String str;
        Scanner s = new Scanner(System.in);
        str = s.nextLine();
        System.out.println("Input line:\n" + str);
        System.out.println(mn.makeTwoBackspaces(str));
        str = mn.letterIntoNumber(str);
        System.out.println(str);
    }

    private String makeTwoBackspaces(String str)
    {
        StringBuilder res = new StringBuilder("");
        for(int i = 0; i < str.length(); i++)
        {
            res.append(str.charAt(i) + "  ");
        }
        res.append("\n");
        return res.toString();
    }

    private String letterIntoNumber(String str)
    {
        StringBuilder res = new StringBuilder("");
        Boolean charFound = false;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < alphabet.length(); j++)
            {
                if (Character.toLowerCase(str.charAt(i)) == alphabet.charAt(j))
                {
                    res.append('(');
                    res.append(j + 1);
                    res.append(')');
                    charFound = true;
                }
            }
            if (!charFound)
                res.append(str.charAt(i));
            charFound = false;
        }
        return res.toString();
    }
}
