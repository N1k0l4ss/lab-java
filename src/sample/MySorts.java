package sample;

import myclasses.Customer;
import java.util.*;

public class MySorts {

    public static List<Customer> sortA(List<Customer> customerList, String parametr){
        List<Customer> sortedByName = new ArrayList<>();
        for (Customer customer : customerList) {
            if (parametr.equals(customer.getFirstName()))
                sortedByName.add(customer);
        }
        return sortedByName;
    }

    public static List<Customer> sortB(List<Customer> customerList, String parametr){
        List<Customer> sorted = new ArrayList<>();
        String[] range = parametr.split("-");
        long min = Long.parseLong(range[0]);
        long max = Long.parseLong(range[1]);
        for (Customer customer : customerList) {
            long number = customer.getNumberCard();
            if (number >= min && number <= max)
                sorted.add(customer);
        }
        return sorted;
    }

    public static Queue<Customer> sortC(List<Customer> customerList) {
        Queue<Customer> sorted = new PriorityQueue<>((o1, o2) -> {
            if (o1.getBalance() < o2.getBalance()) return 1;
            if (o1.getBalance() > o2.getBalance()) return -1;
            return 0;
        });
        for (Customer customer : customerList) {
            if (customer.getBalance() < 0)
                sorted.add(customer);
        }
        return sorted;
    }

    public static Queue<Customer> sortD(List<Customer> customerList) {
        Queue<Customer> sorted = new PriorityQueue<>((o1, o2) -> {
            if (o1.getBalance() < o2.getBalance()) return -1;
            if (o1.getBalance() > o2.getBalance()) return 1;
            if (o1.getBalance() == o2.getBalance()) {
                if (o1.getNumberCard() < o2.getNumberCard()) return -1;
                if (o1.getNumberCard() > o2.getNumberCard()) return 1;
            }
            return 0;
        });
        for (Customer customer : customerList) {
            sorted.add(customer);
        }
        return sorted;
    }

    public static Set<Integer> sortE(List<Customer> customerList) {
        Set<Integer> sortedYears = new TreeSet<>();
        for (Customer customer : customerList) {
            sortedYears.add(customer.getBirthYear());
        }
        return sortedYears;
    }

    public static Map<Integer, Customer> sortF(List<Customer> customerList) {
        Map<Integer, Customer> sortedMap = new HashMap<>();
        for (Customer customer : customerList) {
            if(sortedMap.containsKey(customer.getBirthYear())){
                if (sortedMap.get(customer.getBirthYear()).getBalance() < customer.getBalance()){
                    sortedMap.replace(customer.getBirthYear(), customer);
                }
            }
            else{
                sortedMap.put(customer.getBirthYear(), customer);
            }
        }
        return sortedMap;
    }
}