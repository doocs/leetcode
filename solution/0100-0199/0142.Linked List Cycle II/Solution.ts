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

function detectCycle(head: ListNode | null): ListNode | null {
    let slow = head,
        fast = head;
    while (fast) {
        slow = slow.next;
        if (!fast.next) return null;
        fast = fast.next.next;

        if (fast == slow) {
            let cur = head;
            while (cur != slow) {
                slow = slow.next;
                cur = cur.next;
            }
            return cur;
        }
    }
    return null;
}
