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

function deleteDuplicatesUnsorted(head: ListNode | null): ListNode | null {
    const cnt: Map<number, number> = new Map();
    for (let cur = head; cur; cur = cur.next) {
        const x = cur.val;
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const dummy = new ListNode(0, head);
    for (let pre = dummy, cur = head; cur; cur = cur.next) {
        if (cnt.get(cur.val)! > 1) {
            pre.next = cur.next;
        } else {
            pre = cur;
        }
    }
    return dummy.next;
}
