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
    let ans = [];
    for (; !!head; head = head.next) {
        ans.unshift(head.val);
    }
    return ans;
};
