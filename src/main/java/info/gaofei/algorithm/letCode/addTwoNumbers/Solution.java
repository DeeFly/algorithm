package info.gaofei.algorithm.letCode.addTwoNumbers;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 示例：

 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 * Created by GaoQingming on 2018/11/23 0023.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1);
        ListNode temp = l2;
        for (int i = 0; i < 9; i++) {
            temp.next = new ListNode(9);
            temp = temp.next;
        }
        ListNode result = new Solution().addTwoNumbers(l1, l2);
        printListNode(result);
    }

    private static void printListNode(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        double firstNum = parseNode(l1);
        double secondNum = parseNode(l2);
        return parseNum(firstNum + secondNum);
    }

    private ListNode parseNum(double i) {
        double high = i / 10;
        double suffix = i % 10;
        ListNode result = new ListNode((int) suffix);
        ListNode temp = result;

        while (high >= 1) {
            suffix = high % 10;
            high = high / 10;
            ListNode listNode = new ListNode((int) suffix);
            temp.next = listNode;
            temp = listNode;
        }

        return result;
    }

    private double parseNode(ListNode node) {
        double result = 0;
        for (int i = 0; node != null; i++) {
            double temp = Math.pow(10, i);
            result += node.val * temp;
            node = node.next;
        }
        return result;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
