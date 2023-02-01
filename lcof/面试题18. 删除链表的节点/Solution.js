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
    const dummy = new ListNode(0, head);
    for (let cur = dummy; cur.next; cur = cur.next) {
        if (cur.next.val == val) {
            cur.next = cur.next.next;
            break;
        }
    }
    return dummy.next;
};
