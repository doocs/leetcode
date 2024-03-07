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

function frequenciesOfElements(head: ListNode | null): ListNode | null {
    const cnt: Map<number, number> = new Map();
    for (; head; head = head.next) {
        cnt.set(head.val, (cnt.get(head.val) || 0) + 1);
    }
    const dummy = new ListNode();
    for (const val of cnt.values()) {
        dummy.next = new ListNode(val, dummy.next);
    }
    return dummy.next;
}
