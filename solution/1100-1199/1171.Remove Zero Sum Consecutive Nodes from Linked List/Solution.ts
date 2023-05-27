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

function removeZeroSumSublists(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    const last = new Map<number, ListNode>();
    let s = 0;
    for (let cur = dummy; cur; cur = cur.next) {
        s += cur.val;
        last.set(s, cur);
    }
    s = 0;
    for (let cur = dummy; cur; cur = cur.next) {
        s += cur.val;
        cur.next = last.get(s)!.next;
    }
    return dummy.next;
}
