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
    const nums = [];
    for (; head; head = head.next) {
        nums.push(head.val);
    }
    const stk: number[] = [];
    for (const v of nums) {
        while (stk.length && stk.at(-1)! < v) {
            stk.pop();
        }
        stk.push(v);
    }
    const dummy = new ListNode();
    head = dummy;
    for (const v of stk) {
        head.next = new ListNode(v);
        head = head.next;
    }
    return dummy.next;
}
