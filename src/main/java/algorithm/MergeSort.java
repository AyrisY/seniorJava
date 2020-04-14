package algorithm;

/**
 * 归并排序
 */
public class MergeSort {

    public static ListNode sort(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode middleNode = null;
        ListNode s = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            middleNode = s;
            s = s.next;
            f = f.next.next;
        }

        middleNode.next = null;
        ListNode leftNode = sort(head);
        ListNode rightNode = sort(s);
        return merge(leftNode, rightNode);
    }

    public static ListNode merge(ListNode leftNode, ListNode rightNode) {
        ListNode zeroNode = new ListNode(0);
        ListNode curNode = zeroNode;
        while (leftNode != null && rightNode != null) {
            if (leftNode.val < rightNode.val) {
                curNode.next = leftNode;
                curNode = curNode.next;
                leftNode = leftNode.next;
            } else {
                curNode.next = rightNode;
                curNode = curNode.next;
                rightNode = rightNode.next;
            }
        }
        if (leftNode != null) {
            curNode.next = leftNode;
        }
        if (rightNode != null) {
            curNode.next = rightNode;
        }
        return zeroNode.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        printList(head);
        ListNode sortedList = sort(head);
        System.out.println();
        printList(sortedList);

    }
}
