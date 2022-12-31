/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} left
 * @param {number} right
 * @return {ListNode}
 */
var reverseBetween = function (head, left, right) {
    if (!head.next || left == right) {
        return head;
    }
    const dummy = new ListNode(0, head);
    let pre = dummy;
    for (let i = 0; i < left - 1; ++i) {
        pre = pre.next;
    }
    const p = pre;
    const q = pre.next;
    let cur = q;
    for (let i = 0; i < right - left + 1; ++i) {
        const t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    p.next = pre;
    q.next = cur;
    return dummy.next;
};
