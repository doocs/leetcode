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

function partition(head: ListNode | null, x: number): ListNode | null {
    if (head == null) {
        return head;
    }
    let cur = head;
    while (cur.next != null) {
        let node = cur.next;
        if (node.val < x) {
            [head, node.next, cur.next] = [node, head, node.next];
        } else {
            cur = cur.next;
        }
    }
    return head;
}
