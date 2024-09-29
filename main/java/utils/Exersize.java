package utils;
import java.util.*;

public class Exersize {
    LinkedHashMap<Integer, List<String>> book = new LinkedHashMap<>();

    public void addBook(Integer ISBN,String bookName, String authorName){
        book.put(ISBN, Arrays.asList(bookName, authorName));
    }
    public void retrieveBook(Integer ISBN){
        System.out.println("ISBN "+ ISBN+" : "+"bookName: "+book.get(ISBN).get(0)+" authorName: "+book.get(ISBN).get(1));
    }
    public void retrieveAllBook(){
        for (Map.Entry<Integer, List<String>> entry : book.entrySet()) {
            System.out.println("ISBN "+ entry.getKey()+" : "+"bookName: "+entry.getValue().get(0)+" authorName: "+entry.getValue().get(1));
        }
    }

    public void retrieveAllSortedBook(){
        TreeMap<Integer, List<String>> bookTree = new TreeMap<>(book);
        for (Map.Entry<Integer, List<String>> entry : bookTree.entrySet()) {
            System.out.println("ISBN "+ entry.getKey()+" : "+"bookName: "+entry.getValue().get(0)+" authorName: "+entry.getValue().get(1));
        }
    }


    public static void main(String[] args) {
        Exersize exersize = new Exersize();
        exersize.addBook(1234,"Java","James");
        exersize.addBook(1236,"C++","Bjarne");
        exersize.addBook(1235,"Python","Guido");

        exersize.retrieveBook(1236);
        exersize.retrieveAllBook();
        exersize.retrieveAllSortedBook();




    }

}
