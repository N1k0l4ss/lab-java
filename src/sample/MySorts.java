package sample;
import myclasses.Customer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MySorts {
    // By name
    public static List<Customer> sortA(List<Customer> customerList, String parametr){
        return customerList.stream()
                .filter(customer -> parametr.equals(customer.getFirstName()))
                .collect(Collectors.toList());
    }
    // By credit number rage
    public static List<Customer> sortB(List<Customer> customerList, String parametr){
        String[] rage = parametr.split("-");
        long min = Long.parseLong(rage[0]);
        long max = Long.parseLong(rage[1]);
        return customerList.stream()
                .filter(c -> c.getNumberCard() >= min && c.getNumberCard() <= max)
                .collect(Collectors.toList());
    }
    // Owes by increasing of balance
    public static List<Customer> sortC(List<Customer> customerList) {
        return customerList.stream()
                .filter(c -> c.getBalance() < 0)
                .sorted(Comparator.comparing(Customer::getBalance).reversed())
                .collect(Collectors.toList());
    }
    // by increasing of balance, when they have the same by card number
    public static List<Customer> sortD(List<Customer> customerList) {
        return customerList.stream()
                .sorted(Comparator.comparing(Customer::getBalance).thenComparing(Customer::getNumberCard))
                .collect(Collectors.toList());
    }
    // Birth years
    public static String sortE(List<Customer> customerList) {
        StringBuilder sb = new StringBuilder();
        customerList.stream()
                .sorted(Comparator.comparing(Customer::getBirthYear))
                .map(Customer::getBirthYear)
                .collect(Collectors.toSet())
                .forEach(c -> sb.append(c + "\n"));
        return sb.toString();
    }
    // The most rich in year of birth
    public static List<Customer> sortF(List<Customer> customerList) {
        Map<Integer, Customer> map = new HashMap<>();
        customerList.stream().sorted(Comparator.comparing(Customer::getBirthYear).thenComparing(Customer::getBalance)).forEach(c-> map.put(c.getBirthYear(), c));
        List<Customer> list = new ArrayList<>();
        for (Customer customer : map.values()) {
            list.add(customer);
        }
        return list;
    }
}
