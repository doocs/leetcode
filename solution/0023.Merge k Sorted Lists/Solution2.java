/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = new ListNode(0), move = res;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(3, new Comparator<ListNode>() {
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            move.next = poll;
            move = move.next;

            if (poll.next != null) {
                queue.offer(poll.next);
            }
        }
        return res.next;
    }
}