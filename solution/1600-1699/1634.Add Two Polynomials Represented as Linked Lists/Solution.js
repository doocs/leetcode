/**
 * Definition for polynomial singly-linked list.
 * function PolyNode(x=0, y=0, next=null) {
 *     this.coefficient = x;
 *     this.power = y;
 *     this.next = next;
 * }
 */

/**
 * @param {PolyNode} poly1
 * @param {PolyNode} poly2
 * @return {PolyNode}
 */
var addPoly = function (poly1, poly2) {
    const dummy = new PolyNode();
    let curr = dummy;
    while (poly1 && poly2) {
        if (poly1.power > poly2.power) {
            curr.next = poly1;
            poly1 = poly1.next;
            curr = curr.next;
        } else if (poly1.power < poly2.power) {
            curr.next = poly2;
            poly2 = poly2.next;
            curr = curr.next;
        } else {
            const c = poly1.coefficient + poly2.coefficient;
            if (c != 0) {
                curr.next = new PolyNode(c, poly1.power);
                curr = curr.next;
            }
            poly1 = poly1.next;
            poly2 = poly2.next;
        }
    }
    curr.next = poly1 || poly2;
    return dummy.next;
};
