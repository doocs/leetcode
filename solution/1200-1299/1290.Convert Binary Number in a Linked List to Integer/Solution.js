/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {number}
 */
var getDecimalValue = function (head) {
    let res = 0;
    while (head != null) {
        res = (res << 1) + head.val;
        head = head.next;
    }
    return res;
};
