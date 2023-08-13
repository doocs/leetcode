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

function doubleIt(head: ListNode | null): ListNode | null {
    head = reverse(head);
    const dummy = new ListNode();
    let cur = dummy;
    let mul = 2;
    let carry = 0;
    while (head) {
        const x = head.val * mul + carry;
        carry = Math.floor(x / 10);
        cur.next = new ListNode(x % 10);
        cur = cur.next;
        head = head.next;
    }
    if (carry) {
        cur.next = new ListNode(carry);
    }
    return reverse(dummy.next);
}

function reverse(head: ListNode | null): ListNode | null {
    const dummy = new ListNode();
    let cur = head;
    while (cur) {
        const next = cur.next;
        cur.next = dummy.next;
        dummy.next = cur;
        cur = next;
    }
    return dummy.next;
}
