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
    const vis: Set<number> = new Set();
    let pre: ListNode = new ListNode(0, head);
    while (pre.next) {
        if (vis.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            vis.add(pre.next.val);
            pre = pre.next;
        }
    }
    return head;
}
