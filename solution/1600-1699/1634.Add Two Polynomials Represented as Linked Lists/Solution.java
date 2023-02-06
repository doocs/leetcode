/**
 * Definition for polynomial singly-linked list.
 * class PolyNode {
 *     int coefficient, power;
 *     PolyNode next = null;

 *     PolyNode() {}
 *     PolyNode(int x, int y) { this.coefficient = x; this.power = y; }
 *     PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next =
 next; }
 * }
 */

class Solution {
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
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