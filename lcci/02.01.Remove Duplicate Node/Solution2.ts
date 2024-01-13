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
    let n1 = head;
    while (n1 != null) {
        let n2 = n1;
        while (n2.next != null) {
            if (n1.val === n2.next.val) {
                n2.next = n2.next.next;
            } else {
                n2 = n2.next;
            }
        }
        n1 = n1.next;
    }
    return head;
}
