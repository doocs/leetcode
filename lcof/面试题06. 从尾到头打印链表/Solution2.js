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
    if (!head) {
        return [];
    }
    const ans = reversePrint(head.next);
    ans.push(head.val);
    return ans;
};
