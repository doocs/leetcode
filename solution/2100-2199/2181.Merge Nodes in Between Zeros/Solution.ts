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

function mergeNodes(head: ListNode | null): ListNode | null {
    const dummy = new ListNode();
    let tail = dummy;
    let s = 0;
    for (let cur = head.next; cur; cur = cur.next) {
        if (cur.val) {
            s += cur.val;
        } else {
            tail.next = new ListNode(s);
            tail = tail.next;
            s = 0;
        }
    }
    return dummy.next;
}
