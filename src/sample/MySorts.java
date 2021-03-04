package sample;

import myclasses.Customer;

import java.util.*;
import java.util.stream.Collectors;

//public class MySorts { // Lab 7
//
//    public static List<Customer> sortA(List<Customer> customerList, String parametr){
//        List<Customer> sortedByName = new ArrayList<>();
//        for (Customer customer : customerList) {
//            if (parametr.equals(customer.getFirstName()))
//                sortedByName.add(customer);
//        }
//        return sortedByName;
//    }
//
//    public static List<Customer> sortB(List<Customer> customerList, String parametr){
//        List<Customer> sorted = new ArrayList<>();
//        String[] range = parametr.split("-");
//        long min = Long.parseLong(range[0]);
//        long max = Long.parseLong(range[1]);
//        for (Customer customer : customerList) {
//            long number = customer.getNumberCard();
//            if (number >= min && number <= max)
//                sorted.add(customer);
//        }
//        return sorted;
//    }
//
//    public static Queue<Customer> sortC(List<Customer> customerList) {
//        Queue<Customer> sorted = new PriorityQueue<>((o1, o2) -> {
//            if (o1.getBalance() < o2.getBalance()) return 1;
//            if (o1.getBalance() > o2.getBalance()) return -1;
//            return 0;
//        });
//        for (Customer customer : customerList) {
//            if (customer.getBalance() < 0)
//                sorted.add(customer);
//        }
//        return sorted;
//    }
//
//    public static Queue<Customer> sortD(List<Customer> customerList) {
//        Queue<Customer> sorted = new PriorityQueue<>((o1, o2) -> {
//            if (o1.getBalance() < o2.getBalance()) return -1;
//            if (o1.getBalance() > o2.getBalance()) return 1;
//            if (o1.getBalance() == o2.getBalance()) {
//                if (o1.getNumberCard() < o2.getNumberCard()) return -1;
//                if (o1.getNumberCard() > o2.getNumberCard()) return 1;
//            }
//            return 0;
//        });
//        for (Customer customer : customerList) {
//            sorted.add(customer);
//        }
//        return sorted;
//    }
//
//    public static Set<Integer> sortE(List<Customer> customerList) {
//        Set<Integer> sortedYears = new TreeSet<>();
//        for (Customer customer : customerList) {
//            sortedYears.add(customer.getBirthYear());
//        }
//        return sortedYears;
//    }
//
//    public static Map<Integer, Customer> sortF(List<Customer> customerList) {
//        Map<Integer, Customer> sortedMap = new HashMap<>();
//        for (Customer customer : customerList) {
//            if(sortedMap.containsKey(customer.getBirthYear())){
//                if (sortedMap.get(customer.getBirthYear()).getBalance() < customer.getBalance()){
//                    sortedMap.replace(customer.getBirthYear(), customer);
//                }
//            }
//            else{
//                sortedMap.put(customer.getBirthYear(), customer);
//            }
//        }
//        return sortedMap;
//    }
//}
//
//
public class MySorts { // lab 8
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
    public static Set<Integer> sortE(List<Customer> customerList) {
        return customerList.stream()
                .sorted(Comparator.comparing(Customer::getBirthYear))
                .map(Customer::getBirthYear)
                .collect(Collectors.toSet());
    }
    // The most rich in year of birth
    public static Map<Integer, Customer> sortF(List<Customer> customerList) {
        Map<Integer, Customer> map = new HashMap<>();
        customerList.stream().sorted(Comparator.comparing(Customer::getBirthYear).thenComparing(Customer::getBalance)).forEach(c-> map.put(c.getBirthYear(), c));
        return map;
    }
}