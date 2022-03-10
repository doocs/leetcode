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

function removeDuplicateNodes(head: ListNode | null): ListNode | null {
    if (head == null) {
        return head;
    }
    const set = new Set<number>([head.val]);
    let cur = head;
    while (cur.next != null) {
        if (set.has(cur.next.val)) {
            cur.next = cur.next.next;
        } else {
            set.add(cur.next.val);
            cur = cur.next;
        }
    }
    return head;
}
