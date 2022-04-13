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

function mergeNodes(head: ListNode | null): ListNode | null {
    let dummy = new ListNode(-1);
    let p = dummy;
    let sum = 0;
    head = head.next;
    while (head != null) {
        let cur = head.val;
        if (cur) {
            sum += cur;
        } else {
            p.next = new ListNode(sum);
            p = p.next;
            sum = 0;
        }
        head = head.next;
    }
    return dummy.next;
}
