package firtpart;

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
        mn.printTwoBackspaces(str);
        mn.printLetterNumber(str);
        System.out.println("\n=========================================================\n");
        mn.printTwoBackspaces(str);
        str = mn.letterIntoNumber(str);
        System.out.println(str);
    }

    private void printLetterNumber(String str)
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
                    System.out.print((j + 1) + " ");
                    charFound = true;
                }
            }
            if (!charFound || str.charAt(i) == ' ')
                System.out.print(' ');;
            charFound = false;
        }
        System.out.print("\n");
    }

    private void printTwoBackspaces(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            System.out.print(str.charAt(i) + "  ");
        }
        System.out.print("\n");
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
                if (str.charAt(i)) == alphabet.charAt(j))
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
