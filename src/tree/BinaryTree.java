package tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phongpham on 8/14/15.
 */
public class BinaryTree {

    public static final int IN_ORDER = 0;
    public static final int PRE_ORDER = 1;
    public static final int POST_ORDER = 2;

    private Node root;

    public Node getRoot(){
        return this.root;
    }

    public int height(Node n){
        return n != null ? n.getHeight() : -1;
    }

    public void insert(String d){
        root = insert(d, root);
    }

    public Node insert(String d, Node n){
        if(n == null){
            n = new Node(d, null);
        }else{
            int cmpVal = n.getData().compareTo(d);
            if(cmpVal > 0){
                n.setLeft(insert(d, n.getLeft()));
                if((height(n.getLeft()) - height(n.getRight()) >= 2)){
                    cmpVal = n.getLeft().getData().compareTo(d);
                    if(cmpVal < 0){
                        n = doubleWithLeftChild(n);
                    }else{
                        n = rotateWithLeftChild(n);
                    }
                }
            }else if(cmpVal < 0){
                n.setRight(insert(d, n.getRight()));
                if((height(n.getRight()) - height(n.getLeft())) >= 2){
                    cmpVal = n.getRight().getData().compareTo(d);
                    if(cmpVal < 0){
                        n = rotateWithRightChild(n);
                    }else{
                        n = doubleWithRightChild(n);
                    }
                }
            }
            n.setHeight(Math.max(height(n.getLeft()), height(n.getRight())) + 1);
            n.setLevel(Math.min(height(n.getLeft()), height(n.getRight())) + 1);
        }
        return n;
    }

    /* Rotate binary tree node with left child */
    private Node rotateWithLeftChild(Node k2)
    {
        Node k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
        return k1;
    }

    /* Rotate binary tree node with right child */
    private Node rotateWithRightChild(Node k1)
    {
        Node k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        k1.setHeight(Math.max( height( k1.getLeft() ), height( k1.getRight()) )  + 1);
        k2.setHeight(Math.max( height( k2.getRight() ), k1.getHeight())  + 1);
        return k2;
    }
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child */
    private Node doubleWithLeftChild(Node k3)
    {
        k3.setLeft(rotateWithRightChild(k3.getLeft()));
        return rotateWithLeftChild( k3 );
    }
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child */
    private Node doubleWithRightChild(Node k1)
    {
        k1.setRight(rotateWithLeftChild( k1.getRight() ));
        return rotateWithRightChild( k1 );
    }

    public int add(String d, Node r){
        if(root == null){
            root = new Node(d, null);
            return -1;
        }else{
            if(r == null && this.root != null){
                r = this.root;
            }else if(r == null && this.root == null){
                return -1;
            }
            int cmpVal = r.getData().compareToIgnoreCase(d);
            if(cmpVal > 0){
                if(r.getLeft() == null){
                    r.setLeft(new Node(d, r));
                    return 1;
                }else{
                    int position = add(d, r.getLeft());
                    if(position == 1){
                        r.updateHeight(position, true);
                    }
                    return position;
                }
            }else if(cmpVal < 0){
                if(r.getRight() == null){
                    r.setRight(new Node(d, r));
                    return 2;
                }else{
                    int position = add(d, r.getRight());
                    if(position == 2){
                        r.updateHeight(position, true);
                    }
                    return position;

                }
            }else{
                return -1;
            }
        }
    }

    public void printTree(int orderType){
        switch(orderType){
            case IN_ORDER:
                inOrder();
                break;
            case PRE_ORDER:
                preOrder();
                break;
            case POST_ORDER:
                postOrder();
                break;
            default:
        }
    }

    public void printTree(){
        if(root != null){
            StringBuilder sb = new StringBuilder();
            Node r = null;
            List<Node> nodes = new ArrayList<Node>();
            nodes.add(this.root);
            int height = this.root.getHeight();
            do{
                if(nodes.size() > 0){
                    r = nodes.get(0);
                    nodes.remove(0);
                }
                if(r != null){

                    if(height != r.getLevel()){
                       height = r.getLevel();
                        sb.append("\n");
                    }
                    sb.append(r.getData() + "(" + r.getHeight() + ")" + " ");
                    if(r.getLeft() != null){
                        nodes.add(r.getLeft());
                    }
                    if(r.getRight() != null){
                        nodes.add(r.getRight());
                    }
                }
            }while(nodes.size() != 0);
            System.out.println(sb.toString());
        }
    }

    public void inOrder(){
        List<Node> nodes = new ArrayList<Node>();
        doInOrder(this.root, nodes);
        StringBuilder sb = new StringBuilder();
        for(Node node : nodes){
            sb.append(node.getData() + " ");
        }
        System.out.println(sb.toString());
    }

    public void doInOrder(Node n, List<Node> nodes){
        if(n != null){
            if(n.getLeft() != null){
                doInOrder(n.getLeft(), nodes);
            }
            nodes.add(n);
            if(n.getRight() != null){
                doInOrder(n.getRight(), nodes);
            }
        }
    }

    public void preOrder(){
        List<Node> nodes = new ArrayList<Node>();
        doPreOrder(this.root, nodes);
        StringBuilder sb = new StringBuilder();
        for(Node node : nodes){
            sb.append(node.getData() + " ");
        }
        System.out.println(sb.toString());
    }

    public void doPreOrder(Node n, List<Node> nodes){
        if(n != null){
            nodes.add(n);
            if(n.getLeft() != null){
                doPreOrder(n.getLeft(), nodes);
            }
            if(n.getRight() != null){
                doPreOrder(n.getRight(), nodes);
            }
        }
    }

    public void postOrder(){
        List<Node> nodes = new ArrayList<Node>();
        doPostOrder(this.root, nodes);
        StringBuilder sb = new StringBuilder();
        for(Node node : nodes){
            sb.append(node.getData() + " ");
        }
        System.out.println(sb.toString());
    }

    public void doPostOrder(Node n, List<Node> nodes){
        if(n != null){
            if(n.getLeft() != null){
                doPreOrder(n.getLeft(), nodes);
            }
            if(n.getRight() != null){
                doPreOrder(n.getRight(), nodes);
            }
            nodes.add(n);
        }
    }

    public void zigzag(){
        List<List<Node>> nodes = new ArrayList<List<Node>>();
        if(this.root != null){
            doZigZag(nodes, 0, this.root);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<nodes.size(); i++){
            sb.append("At level " + (i+1) + ": ");
            for(Node n : nodes.get(i)){
                sb.append(n.getData() + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void doZigZag(List<List<Node>> nodes, int level, Node n){
        if(n == null) return;
        if(nodes.size() <= level){
            nodes.add(new ArrayList<Node>());
        }
        if(level%2 == 0){
            nodes.get(level).add(n);
        }else{
            nodes.get(level).add(0, n);
        }
        doZigZag(nodes, level + 1, n.getLeft());
        doZigZag(nodes, level + 1, n.getRight());
    }

    public void doZigZag(List<Node> nodes, int direction, int row){
        if(nodes.size() > 0){
            List<Node> nextNodes = new ArrayList<Node>();
            StringBuilder sb = new StringBuilder();
            int pointer = row%3 != 0 ? nodes.size()-1 : 0;
            int delta = row%3 != 0 ? -1 : 1;
            int nextDirection = direction * -1;
            System.out.println(row + " start from " +  pointer);
            int nextPointer = pointer + delta;
            if(nextPointer >= 0 && nextPointer <= nodes.size()){
                Node n1 = nodes.get(pointer);
                Node n2 = nodes.get(nextPointer);
                int compVal = n1.getData().compareTo(n2.getData());
                if(compVal > 0){
                    nextDirection = -1;
                }else{
                    nextDirection = 1;
                }
            }

            for(int i=0; i<nodes.size(); i++){
                Node node = nodes.get(pointer);
                pointer += delta;
                sb.append(node.getData() + " ");

                if(nextDirection == 1){
                    if(node.getLeft() != null){
                        nextNodes.add(node.getLeft());
                    }
                    if(node.getRight() != null){
                        nextNodes.add(node.getRight());
                    }
                }else{
                    if(node.getRight() != null){
                        nextNodes.add(node.getRight());
                    }
                    if(node.getLeft() != null){
                        nextNodes.add(node.getLeft());
                    }
                }
            }
            System.out.println(sb.toString());
            doZigZag(nextNodes, nextDirection, row+1);
        }
    }

    public Node advanceWithTraverse(String d){
        Node result = doAdvanceWithTraverse(root, d, null);
        System.out.println(d + " advances to " + (result != null ? result.getData() : result));
        return result;
    }

    public Node doAdvanceWithTraverse(Node n, String d, Node prevNode){
        Node result = null;
        if(n != null){
            int cmpVal = n.getData().compareTo(d);
            if(cmpVal == 0){
                if(n.getRight() != null){
                    result = n.getRight();
                    while(result.getLeft() != null){
                        result = result.getLeft();
                    }
                }else{
                    result = prevNode;
                }
            }else{
                if(cmpVal > 0){
                    prevNode = n;
                }
                result = doAdvanceWithTraverse(cmpVal > 0 ? n.getLeft() : n.getRight(), d, prevNode);
            }
        }
        return result;
    }

    public Node advance(String d){
        Node input = getNode(d, false);
        Node result = doAdvance(input, this.root);
        if(input != null){
            System.out.println(d + " advances to " + (result != null ? result.getData() : "null"));
        }
        return result;
    }

    public Node doAdvance(Node input, Node r){
        Node result = null;
        if(input != null && r != null){
            if(input.getRight() != null){
                Node potential = input.getRight();
                while(potential.getLeft() != null){
                    potential = potential.getLeft();
                }
                result = potential;
            }else{
                Node parent = input;
                boolean stop = false;
                do{
                    parent = getParent(parent.getData(), false);
                    if(parent != null){
                        if(parent.getData().compareToIgnoreCase(input.getData()) > 0){
                            result = parent;
                            stop = true;
                        }
                    }
                }while(parent != null && !stop);
            }
        }
        return result;
    }

    public Node getNode(String d, boolean print){
        Node result = doGetNode(d, this.root);
        if(print){
            System.out.println("Found node for " + d + "? " + (result != null ? "yes" : "no"));
        }
        return result;
    }

    public Node doGetNode(String d, Node r){
        Node result = null;
        if(d != null){
            int cmpVal = r.getData().compareToIgnoreCase(d);
            if(cmpVal == 0){
                result = r;
            }else if(cmpVal > 0 && r.getLeft() != null){
                result = doGetNode(d, r.getLeft());
            }else if(cmpVal < 0 && r.getRight() != null){
                result = doGetNode(d, r.getRight());
            }
        }
        return result;
    }

    public Node getParent(String d, boolean print){
        Node input = getNode(d, false);
        Node result = doGetParent(input, this.root);
        if(print){
            System.out.println("Parent of " + d + " is " + (result != null ? result.getData() : "null"));
        }

        return result;
    }

    public Node doGetParent(Node input, Node r){
        if(input == null){
            return null;
        }
        Node result = null;
        if(r != null &&
                (r.getLeft() != null && r.getLeft().equals(input))
                || (r.getRight() != null && r.getRight().equals(input))){
            return r;
        }else if(r != null){
            int cmpVal = r.getData().compareToIgnoreCase(input.getData());
            if(cmpVal > 0){
                result = doGetParent(input, r.getLeft());
            }else if(cmpVal < 0){
                result = doGetParent(input, r.getRight());
            }
        }
        return result;
    }

    public void printWithLevel(){
        List<List<Node>> nodes = new ArrayList<List<Node>>();
        doPrintWithLevel(root, nodes, 0);
        for(int i=0; i<nodes.size(); i++){
            List<Node> levelNodes = nodes.get(i);
            for(Node n : levelNodes){
                System.out.print(n.getData() + "(" + n.getHeight() + ") ");
            }
            System.out.println();
        }
    }

    public void doPrintWithLevel(Node n, List<List<Node>> nodes, int level){
        if(n == null){
            return;
        }
        if(nodes.size() <= level){
            nodes.add(new ArrayList<Node>());
        }
        nodes.get(level).add(n);
        doPrintWithLevel(n.getLeft(), nodes, level + 1);
        doPrintWithLevel(n.getRight(), nodes, level + 1);
    }

    public void doPrintWithLevelLoop(Node n, boolean zigzag){
        if(n != null){
            List<Node> ltrNodes = new ArrayList<Node>();
            List<Node> rtlNodes = new ArrayList<Node>();
            ltrNodes.add(n);
            while(ltrNodes.size() > 0){
                while(ltrNodes.size() > 0){
                    n = ltrNodes.remove(0);
                    System.out.print(n.getData() + " ");
                    if(zigzag){
                        if(n.getLeft() != null){
                            rtlNodes.add(0, n.getLeft());
                        }
                        if(n.getRight() != null){
                            rtlNodes.add(0, n.getRight());
                        }
                    }else{
                        if(n.getLeft() != null){
                            rtlNodes.add(n.getLeft());
                        }
                        if(n.getRight() != null){
                            rtlNodes.add(n.getRight());
                        }
                    }
                }

                System.out.println();
                while(rtlNodes.size() > 0){
                    n = rtlNodes.remove(0);
                    System.out.print(n.getData() + " ");
                    if(zigzag){
                        if(n.getRight() != null){
                            ltrNodes.add(0, n.getRight());
                        }
                        if(n.getLeft() != null){
                            ltrNodes.add(0, n.getLeft());
                        }
                    }else{
                        if(n.getLeft() != null){
                            ltrNodes.add(n.getLeft());
                        }
                        if(n.getRight() != null){
                            ltrNodes.add(n.getRight());
                        }
                    }

                }
                if(ltrNodes.size() > 0){
                    System.out.println();
                }
            }
        }
    }

}
