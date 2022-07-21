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

function hasCycle(head: ListNode | null): boolean {
    const set = new Set<ListNode>();
    let node = head;
    while (node != null) {
        if (set.has(node)) {
            return true;
        }
        set.add(node);
        node = node.next;
    }
    return false;
}
