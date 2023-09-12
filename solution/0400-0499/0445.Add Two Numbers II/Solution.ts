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

function addTwoNumbers(l1: ListNode | null, l2: ListNode | null): ListNode | null {
    const s1: number[] = [];
    const s2: number[] = [];
    for (; l1; l1 = l1.next) {
        s1.push(l1.val);
    }
    for (; l2; l2 = l2.next) {
        s2.push(l2.val);
    }
    const dummy = new ListNode();
    let carry = 0;
    while (s1.length || s2.length || carry) {
        const s = (s1.pop() ?? 0) + (s2.pop() ?? 0) + carry;
        // const node = new ListNode(s % 10, dummy.next);
        // dummy.next = node;
        dummy.next = new ListNode(s % 10, dummy.next);
        carry = Math.floor(s / 10);
    }
    return dummy.next;
}
