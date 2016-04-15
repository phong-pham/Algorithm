package linked_list;

/**
 * Created by phongpham on 5/12/15.
 */
public class LinkedList {

    public static void main(String[] args){

        MyLinkedList ll = new MyLinkedList();
        for(int i=0; i<100; i++){
            int val = (int)(Math.random()*100);
            ll.add(val);
        }
        ll.printLinkedList();
        reverseLinkedList(ll).printLinkedList();

        MyLinkedList l1 = new MyLinkedList();
        for(int i=0; i<100; i++){
            int val = (int)(Math.random()*10);
            l1.add(val);
        }
//        l1.add(3);

        MyLinkedList l2 = new MyLinkedList();
        for(int i=0; i<100; i++){
            int val = (int)(Math.random()*10);
            l2.add(val);
        }


        System.out.print("l1: ");
        l1.printLinkedList();
        System.out.print("l2: ");
        l2.printLinkedList();

        addTwoLinkedList(l1, l2).printLinkedList();


        System.out.print("\nl1: ");
        l1.printLinkedList();
        System.out.print("l2: ");
        l2.printLinkedList();

        addTwoLinkedList(l2, l1).printLinkedList();

        l1 = new MyLinkedList();
//        l1.add(3);
        l1.add(8);
        l2 = new MyLinkedList();
        l2.add(9);
        l2.add(5);

        System.out.print("\nl1: ");
        l1.printLinkedList();
        System.out.print("l2: ");
        l2.printLinkedList();

        addTwoLinkedList(l2, l1).printLinkedList();
    }

    public static MyLinkedList addTwoLinkedList(MyLinkedList l1, MyLinkedList l2){
        if(l1 == null && l2 != null){
            return l2;
        }else if(l1 != null && l2 == null){
            return l1;
        }else if(l1 == null && l2 == null){
            return null;
        }
        MyLinkedList result = new MyLinkedList();
        MyLinkedList r1 = reverseLinkedList(l1), r2 = reverseLinkedList(l2);
        MyLinkedList.Node h1 =r1.getRoot(), h2 = r2.getRoot();
        int carry = 0;
        do{
            int val = carry;
            if(h1 != null){
                val += h1.getData();
                h1 = h1.getNext();
            }
            if(h2 != null){
                val += h2.getData();
                h2 = h2.getNext();
            }
            result.add(val%10);
            carry = val/10;
        }while(h1 != null || h2 != null);
        if(carry > 0){
            result.add(carry);
        }
        reverseLinkedList(l1);
        reverseLinkedList(l2);
        return reverseLinkedList(result);
    }

    public static MyLinkedList reverseLinkedList(MyLinkedList l){
        if(l == null){
            return null;
        }else if(l.getCount() == 0){
            return new MyLinkedList();
        }
        MyLinkedList.Node head = l.getRoot();
        MyLinkedList.Node second = head.getNext();
//        if(second == null){
//            return l;
//        }
//        MyLinkedList.Node third = second.getNext();
//        if(third == null){
//            second.setNext(head);
//            head.setNext(null);
//            l.setRoot(second);
//            return l;
//        }
        MyLinkedList.Node current = second;
        MyLinkedList.Node previous = head;
        while(current != null){
            MyLinkedList.Node next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        head.setNext(null);
        l.setRoot(previous);
//        int i = 0;
//        do{
//            data[i] = head.getData();
//            head = head.getNext();
//            i++;
//        }while(head != null);
//        MyLinkedList result = new MyLinkedList();
//        for(int j=l.getCount()-1; j>=0; j--){
//            result.add(data[j]);
//        }
        return l;
    }


}


