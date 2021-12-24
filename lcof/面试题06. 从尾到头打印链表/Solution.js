/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {number[]}
 */
var reversePrint = function (head) {
    let node = head;
    let res = [];
    while (node) {
        res.unshift(node.val);
        node = node.next;
    }
    return res;
};
