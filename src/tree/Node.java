package tree;

/**
 * Created by phongpham on 8/14/15.
 */
public class Node {

    private Node parent;
    private String data;
    private Node left;
    private Node right;
    private int height;
    private int level;

    public Node(){

    }

    public Node(String d, Node parent){
        this.data = d;
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void updateHeight(int position, boolean add){

    }
}
