package info.gaofei.algorithm.tree;

/**
 * Created by GaoQingming on 2018/10/3.
 */
public class Main {
    public static void main(String[] args) {
        BalanceTree<Integer> balanceTree = new BalanceTree<>();
        balanceTree.add(1);
        balanceTree.add(10);
        balanceTree.add(23);
        balanceTree.add(8);
        balanceTree.add(665);
        balanceTree.add(234);
        balanceTree.add(89);
        balanceTree.add(45);
        balanceTree.add(87);
        balanceTree.add(34);
        balanceTree.add(7);
        balanceTree.add(78);
        balanceTree.add(56);
        balanceTree.add(30);
        balanceTree.add(563);
        balanceTree.add(123);
        balanceTree.add(976);
        balanceTree.add(25);
        balanceTree.add(64);
        balanceTree.add(3);

        balanceTree.remove(34);
        balanceTree.remove(34);
        balanceTree.remove(3);

        balanceTree.midOrder();
    }
}
