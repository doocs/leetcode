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
    const [l, r] = [new ListNode(), new ListNode()];
    let [tl, tr] = [l, r];
    for (; head; head = head.next) {
        if (head.val < x) {
            tl.next = head;
            tl = tl.next;
        } else {
            tr.next = head;
            tr = tr.next;
        }
    }
    tr.next = null;
    tl.next = r.next;
    return l.next;
};
