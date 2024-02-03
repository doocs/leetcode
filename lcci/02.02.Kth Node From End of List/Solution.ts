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

function kthToLast(head: ListNode | null, k: number): number {
    let [slow, fast] = [head, head];
    while (k--) {
        fast = fast.next;
    }
    while (fast !== null) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow.val;
}
