/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = 0, lenB = 0;
        ListNode p = headA, q = headB;
        while (p != null) {
            p = p.next;
            ++lenA;
        }
        while (q != null) {
            q = q.next;
            ++lenB;
        }
        
        p = headA;
        q = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; ++i) {
                p = p.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; ++i) {
                q = q.next;
            }
        }
        while (p != null && p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
        
    }
}