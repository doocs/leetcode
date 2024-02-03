/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} k
 * @return {number}
 */
var kthToLast = function (head, k) {
    let [slow, fast] = [head, head];
    while (k--) {
        fast = fast.next;
    }
    while (fast !== null) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow.val;
};
