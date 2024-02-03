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
var removeDuplicateNodes = function (head) {
    const vis = new Set();
    let pre = new ListNode(0, head);
    while (pre.next) {
        if (vis.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            vis.add(pre.next.val);
            pre = pre.next;
        }
    }
    return head;
};
