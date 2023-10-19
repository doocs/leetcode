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

function swapNodes(head: ListNode | null, k: number): ListNode | null {
    let [fast, slow] = [head, head];
    while (--k) {
        fast = fast.next;
    }
    const p = fast;
    while (fast.next) {
        fast = fast.next;
        slow = slow.next;
    }
    const q = slow;
    [p.val, q.val] = [q.val, p.val];
    return head;
}
