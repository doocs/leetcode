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

function deleteDuplicates(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(101, head);
    let pev = dummy;
    let cur = dummy;
    let count = 1;
    while (cur != null) {
        if (cur.val !== (cur.next ?? {}).val) {
            if (count === 1) {
                pev = cur;
            } else {
                pev.next = cur.next;
            }
            count = 0;
        }
        cur = cur.next;
        count++;
    }
    return dummy.next;
}
