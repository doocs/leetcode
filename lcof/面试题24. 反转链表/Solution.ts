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

function reverseList(head: ListNode | null): ListNode | null {
    let cur = head;
    let pre = null;
    while (cur != null) {
        const temp = cur.next;
        cur.next = pre;
        pre = cur;
        cur = temp;
    }
    return pre;
}
