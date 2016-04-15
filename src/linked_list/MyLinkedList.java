package linked_list;

/**
 * Created by phongpham on 5/12/15.
 */
public class MyLinkedList{
    private Node root;
    private int count;

    public MyLinkedList(){}

    public MyLinkedList(Node r){
        this.root = r;
    }

    public void add(int data){
        Node newNode = new Node(data);
        if(root == null){
            root = newNode;
            this.count++;
        }else{
            Node head = root;
            while(head.getNext() != null){
                head = head.getNext();
            }
            head.setNext(newNode);
            this.count++;
        }
    }

    public void printLinkedList(){
        StringBuilder sb = new StringBuilder();
        Node head = root;
        if(head != null){
            do{
                sb.append(head.getData() + "\t");
                head = head.getNext();
            }while(head != null);
        }
        String str = sb.toString();
        System.out.println(str.trim());
    }

    public Node getRoot(){return this.root;}
    public void setRoot(Node r){this.root = r;}

    public int getCount(){return this.count;}
    public void setCount(int c){this.count = c;}

    public Node cloneNode(Node n){
        return new Node(n.getData());
    }

    public class Node{

        private int data;
        private Node next;


        public Node(int d){
            this.data = d;
        }

        public int getData(){return this.data;}
        public void setData(int d){ this.data = d;}

        public Node getNext(){return this.next;}
        public void setNext(Node n){this.next = n;}
    }
}