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

function hasCycle(head: ListNode | null): boolean {
    const s: Set<ListNode> = new Set();
    for (; head; head = head.next) {
        if (s.has(head)) {
            return true;
        }
        s.add(head);
    }
    return false;
}
