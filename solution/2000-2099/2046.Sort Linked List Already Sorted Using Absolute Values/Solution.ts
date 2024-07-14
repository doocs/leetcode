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

function sortLinkedList(head: ListNode | null): ListNode | null {
    let [prev, curr] = [head, head.next];
    while (curr !== null) {
        if (curr.val < 0) {
            const t = curr.next;
            prev.next = t;
            curr.next = head;
            head = curr;
            curr = t;
        } else {
            [prev, curr] = [curr, curr.next];
        }
    }
    return head;
}
