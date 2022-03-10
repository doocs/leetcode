/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var addTwoNumbers = function (l1, l2) {
    let carry = 0;
    const dummy = new ListNode(0);
    let cur = dummy;
    while (l1 || l2 || carry) {
        carry += (l1?.val || 0) + (l2?.val || 0);
        cur.next = new ListNode(carry % 10);
        carry = Math.floor(carry / 10);
        cur = cur.next;
        l1 = l1?.next;
        l2 = l2?.next;
    }
    return dummy.next;
};
