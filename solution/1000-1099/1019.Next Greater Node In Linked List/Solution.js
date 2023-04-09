/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {number[]}
 */
var nextLargerNodes = function (head) {
    const nums = [];
    while (head) {
        nums.push(head.val);
        head = head.next;
    }
    const stk = [];
    const n = nums.length;
    const ans = new Array(n).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && stk[stk.length - 1] <= nums[i]) {
            stk.pop();
        }
        ans[i] = stk.length ? stk[stk.length - 1] : 0;
        stk.push(nums[i]);
    }
    return ans;
};
