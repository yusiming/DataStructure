package linkedList;

/**
 * @Auther yusiming
 * @Date 2018/11/17 15:00
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addAtFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);

        linkedList.delete(2);
        System.out.println(linkedList);
        linkedList.deleteAtFirst();
        System.out.println(linkedList);
        linkedList.deleteAtLast();
        System.out.println(linkedList);
    }
}
