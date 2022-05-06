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

const createStack = (head: ListNode | null) => {
    const res = [];
    while (head != null) {
        res.push(head.val);
        head = head.next;
    }
    return res;
};

function addTwoNumbers(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    const stack1 = createStack(l1);
    const stack2 = createStack(l2);
    const dummy = new ListNode();
    let sum = 0;
    while (stack1.length !== 0 || stack2.length !== 0 || sum !== 0) {
        sum += (stack1.pop() ?? 0) + (stack2.pop() ?? 0);
        dummy.next = new ListNode(sum % 10, dummy.next);
        sum = Math.floor(sum / 10);
    }
    return dummy.next;
}
