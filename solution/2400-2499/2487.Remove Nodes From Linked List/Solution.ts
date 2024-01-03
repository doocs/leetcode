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

function removeNodes(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(Infinity, head);
    const stk: ListNode[] = [dummy];
    for (let cur = head; cur; cur = cur.next) {
        while (stk.at(-1)!.val < cur.val) {
            stk.pop();
        }
        stk.at(-1)!.next = cur;
        stk.push(cur);
    }
    return dummy.next;
}
