/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var deleteDuplicates = function (head) {
    let cur = head;
    let pre = new ListNode(0);
    pre.next = head;
    let dummy = pre;
    let rep = false;
    if (!head || !head.next) {
        return head;
    }
    while (cur) {
        while (cur.next && cur.val == cur.next.val) {
            cur = cur.next;
            rep = true;
        }
        if (rep) {
            pre.next = cur.next;
            cur = cur.next;
        } else {
            pre = cur;
            cur = cur.next;
        }
        rep = false;
    }
    return dummy.next;
};
