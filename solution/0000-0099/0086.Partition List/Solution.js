/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} x
 * @return {ListNode}
 */
var partition = function (head, x) {

    if (!head || !head.next) {
        return head;
    }
    const dummy1 = new ListNode(-1), dummy2 = new ListNode(-1);
    let cur1 = dummy1, cur2 = dummy2;
    let cur = head;
    while (cur) {
        if (cur.val < x) {
            cur1.next = cur;
            cur1 = cur1.next;
        } else {
            cur2.next = cur;
            cur2 = cur2.next;
        }
        cur = cur.next;
    }
    cur2.next = null;
    cur1.next = dummy2.next;
    return (dummy1.next);
};
