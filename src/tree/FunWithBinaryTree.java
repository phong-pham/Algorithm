package tree;

import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;
import tree.BinaryTree;

/**
 * Created by phongpham on 8/14/15.
 */
public class FunWithBinaryTree {

    public static void main(String[] args){

//                      H
//               D             L
//           B      F       J     N
//         A  C   E   G   I  K  M  O

//                      H
//               D             L
//           B      F       J     P
//         A  C   E   G   I  K  N  Q
//                             M O   R
        BinaryTree bt = new BinaryTree();
        bt.add("H", null);
        bt.add("D", null);
        bt.add("L", null);
        bt.add("B", null);
        bt.add("F", null);
        bt.add("J", null);
        bt.add("N", null);
        bt.add("A", null);
        bt.add("C", null);
        bt.add("E", null);
        bt.add("G", null);
        bt.add("I", null);
        bt.add("K", null);
        bt.add("M", null);
        bt.add("O", null);

        bt.printTree(BinaryTree.IN_ORDER);
        bt.printTree(BinaryTree.PRE_ORDER);
        bt.printTree(BinaryTree.POST_ORDER);

        bt.getNode("A", true);
        bt.getNode("F", true);
        bt.getNode("I", true);
        bt.getNode("Z", true);

        bt.getParent("A", true);
        bt.getParent("F", true);
        bt.getParent("G", true);
        bt.getParent("I", true);
        bt.getParent("O", true);
        bt.getParent("H", true);
        bt.getParent("Z", true);

        System.out.println("\n\nTesting advance:\n");

        bt.advance("A");
        System.out.println();
        bt.advance("B");
        System.out.println();
        bt.advance("C");
        System.out.println();
        bt.advance("D");
        System.out.println();
        bt.advance("E");
        System.out.println();
        bt.advance("F");
        System.out.println();
        bt.advance("G");
        System.out.println();
        bt.advance("H");
        System.out.println();
        bt.advance("I");
        System.out.println();
        bt.advance("J");
        System.out.println();
        bt.advance("K");
        System.out.println();
        bt.advance("L");
        System.out.println();
        bt.advance("M");
        System.out.println();
        bt.advance("N");
        System.out.println();
        bt.advance("O");
        System.out.println();
        bt.advance("Z");

        bt = new BinaryTree();
        bt.insert("D");
        bt.insert("B");
        bt.insert("C");
        bt.insert("A");
        bt.insert("E");
        bt.insert("F");
        bt.insert("G");
        bt.insert("H");
        bt.insert("I");
        bt.insert("J");
        bt.insert("K");
        bt.insert("L");
        bt.insert("M");
        bt.insert("N");
        bt.insert("O");
        bt.insert("P");
        bt.insert("Q");
        bt.insert("R");
        System.out.println("Print tree IN_ORDER");
        bt.printTree(BinaryTree.IN_ORDER);
        System.out.println("Print tree PRE_ORDER");
        bt.printTree(BinaryTree.PRE_ORDER);
        System.out.println("Print tree structure");
        bt.printTree();
        System.out.println("\nDo ZigZag");
        bt.zigzag();
        System.out.println();

        bt.advanceWithTraverse("A");
        System.out.println();
        bt.advanceWithTraverse("B");
        System.out.println();
        bt.advanceWithTraverse("C");
        System.out.println();
        bt.advanceWithTraverse("D");
        System.out.println();
        bt.advanceWithTraverse("E");
        System.out.println();
        bt.advanceWithTraverse("F");
        System.out.println();
        bt.advanceWithTraverse("G");
        System.out.println();
        bt.advanceWithTraverse("H");
        System.out.println();
        bt.advanceWithTraverse("I");
        System.out.println();
        bt.advanceWithTraverse("J");
        System.out.println();
        bt.advanceWithTraverse("K");
        System.out.println();
        bt.advanceWithTraverse("L");
        System.out.println();
        bt.advanceWithTraverse("M");
        System.out.println();
        bt.advanceWithTraverse("N");
        System.out.println();
        bt.advanceWithTraverse("O");
        System.out.println();
        bt.advanceWithTraverse("Z");

        System.out.println("\nPrint tree with level");
        bt.printWithLevel();
        System.out.println("\nPrint tree with level (loop)");
        bt.doPrintWithLevelLoop(bt.getRoot(), false);
        System.out.println("\nPrint tree with level (loop) and zigzag");
        bt.doPrintWithLevelLoop(bt.getRoot(), true);
    }
}
