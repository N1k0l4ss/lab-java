import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String str;
        Scanner s = new Scanner(System.in);
        str = s.nextLine();
        System.out.println(str);
        String res = "";

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < str.length(); i++)
            for (int j = 0; j < alphabet.length(); j++)
            {
//                if (str.charAt(i) == ' ')
//                    res += " ";
                if (str.charAt(i) == alphabet.charAt(j))
                    res += (j+1) + '_';
                else//  Передалать
                    res += str.charAt(i);
            }
    }
}
