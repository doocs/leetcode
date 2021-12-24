/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} val
 * @return {ListNode}
 */
var deleteNode = function (head, val) {
    const dummy = new ListNode(0);
    dummy.next = head;
    let pre = dummy;
    while (pre.next && pre.next.val != val) {
        pre = pre.next;
    }
    pre.next = pre.next ? pre.next.next : null;
    return dummy.next;
};
