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

function rotateRight(head: ListNode | null, k: number): ListNode | null {
    if (k == 0 || head == null || head.next == null) return head;
    // mod n
    let n = 0;
    let p = head;
    while (p != null) {
        ++n;
        p = p.next;
    }
    k %= n;
    if (k == 0) return head;

    let fast = head,
        slow = head;
    for (let i = 0; i < k; ++i) {
        fast = fast.next;
    }
    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    let start = slow.next;
    slow.next = null;
    fast.next = head;
    return start;
}
