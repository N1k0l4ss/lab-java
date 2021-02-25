package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import myclasses.Customer;

import java.util.*;

public class MySorts {

    public static ObservableList<Customer> sortA(List<Customer> customerList, String parametr){
        ObservableList<Customer> sortedByName = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
            if (parametr.equals(customer.getFirstName()))
                sortedByName.add(customer);
        }
        return sortedByName;
    }

    public static ObservableList<Customer> sortB(List<Customer> customerList, String parametr){
        ObservableList<Customer> sorted = FXCollections.observableArrayList();
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

    public static ObservableList<Customer> sortC(List<Customer> customerList) {
        Queue<Customer> sorted = new PriorityQueue<>((o1, o2) -> {
            if (o1.getBalance() < o2.getBalance()) return 1;
            if (o1.getBalance() > o2.getBalance()) return -1;
            return 0;
        });
        for (Customer customer : customerList) {
            if (customer.getBalance() < 0)
                sorted.add(customer);
        }
        ObservableList<Customer> sortedObservable = FXCollections.observableArrayList();
        while(!sorted.isEmpty()) {
            sortedObservable.add(sorted.remove());
        }
        return sortedObservable;
    }

    public static ObservableList<Customer> sortD(List<Customer> customerList) {
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
        ObservableList<Customer> sortedObservable = FXCollections.observableArrayList();
        while(!sorted.isEmpty()) {
            sortedObservable.add(sorted.remove());
        }
        return sortedObservable;
    }

    public static String sortE(List<Customer> customerList) {
        Set<Integer> sortedYears = new TreeSet<>();
        for (Customer customer : customerList) {
            sortedYears.add(customer.getBirthYear());
        }
        StringBuilder str = new StringBuilder();
        for (Integer integer : sortedYears) {
            str.append(integer + "\n");
        }
        return str.toString();
    }

    public static ObservableList<Customer> sortF(List<Customer> customerList) {
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
        ObservableList<Customer> sorted = FXCollections.observableArrayList();
        for (Customer value : sortedMap.values()) {
            sorted.add(value);
        }
        return sorted;
    }
}
