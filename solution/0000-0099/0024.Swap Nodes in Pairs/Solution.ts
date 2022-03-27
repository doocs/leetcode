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

function swapPairs(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let cur = dummy;
    while (cur.next != null && cur.next.next != null) {
        const a = cur.next;
        const b = cur.next.next;
        [a.next, b.next, cur.next] = [b.next, a, b];
        cur = cur.next.next;
    }
    return dummy.next;
}
