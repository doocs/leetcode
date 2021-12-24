/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var reverseList = function (head) {
    let node = head;
    let pre = null;
    while (node) {
        let cur = node;
        node = cur.next;
        cur.next = pre;
        pre = cur;
    }
    return pre;
};
