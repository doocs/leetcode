/**
 * Definition for polynomial singly-linked list.
 * public class PolyNode {
 *     public int coefficient, power;
 *     public PolyNode next;
 *
 *     public PolyNode(int x=0, int y=0, PolyNode next=null) {
 *         this.coefficient = x;
 *         this.power = y;
 *         this.next = next;
 *     }
 * }
 */

public class Solution {
    public PolyNode AddPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode dummy = new PolyNode();
        PolyNode curr = dummy;
        while (poly1 != null && poly2 != null) {
            if (poly1.power > poly2.power) {
                curr.next = poly1;
                poly1 = poly1.next;
                curr = curr.next;
            } else if (poly1.power < poly2.power) {
                curr.next = poly2;
                poly2 = poly2.next;
                curr = curr.next;
            } else {
                int c = poly1.coefficient + poly2.coefficient;
                if (c != 0) {
                    curr.next = new PolyNode(c, poly1.power);
                    curr = curr.next;
                }
                poly1 = poly1.next;
                poly2 = poly2.next;
            }
        }
        if (poly1 == null) {
            curr.next = poly2;
        }
        if (poly2 == null) {
            curr.next = poly1;
        }
        return dummy.next;
    }
}