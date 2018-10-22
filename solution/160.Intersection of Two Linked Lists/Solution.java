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
        if(headA == null || headB == null)
            return null;
        ListNode p = headA;
        ListNode q = headB;
        int pCount = 0;
        int qCount = 0;
        while(p.next != null || q.next != null){
            if(p == q)
                return p;

            if(p.next != null)
                p = p.next;
            else
                qCount++;

            if(q.next != null)
                q = q.next;
            else
                pCount++;
        }
        if(p != q)
            return null;
        p = headA;
        q = headB;
        while(pCount-- != 0){
            p = p.next;
        }
        while(qCount-- != 0){
            q = q.next;
        }
        while(p != q){
            p = p.next;
            q = q.next;
        }
        return p;
    }
}