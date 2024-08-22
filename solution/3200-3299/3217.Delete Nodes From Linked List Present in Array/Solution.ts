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

function modifiedList(nums: number[], head: ListNode | null): ListNode | null {
    const s: Set<number> = new Set(nums);
    const dummy = new ListNode(0, head);
    for (let pre = dummy; pre.next; ) {
        if (s.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            pre = pre.next;
        }
    }
    return dummy.next;
}
