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

function plusOne(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let target = dummy;
    while (head) {
        if (head.val !== 9) {
            target = head;
        }
        head = head.next;
    }
    target.val++;
    for (target = target.next; target; target = target.next) {
        target.val = 0;
    }
    return dummy.val ? dummy : dummy.next;
}
