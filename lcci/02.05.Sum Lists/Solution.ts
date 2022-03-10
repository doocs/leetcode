/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function addTwoNumbers(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    if (l1 == null || l2 == null) {
        return l1 && l2;
    }
    const dummy = new ListNode(0);
    let cur = dummy;
    while (l1 != null || l2 != null) {
        let val = 0;
        if (l1 != null) {
            val += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            val += l2.val;
            l2 = l2.next;
        }
        if (cur.val >= 10) {
            cur.val %= 10;
            val++;
        }
        cur.next = new ListNode(val);
        cur = cur.next;
    }
    if (cur.val >= 10) {
        cur.val %= 10;
        cur.next = new ListNode(1);
    }
    return dummy.next;
}
