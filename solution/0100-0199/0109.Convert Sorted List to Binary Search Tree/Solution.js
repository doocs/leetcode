/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {ListNode} head
 * @return {TreeNode}
 */
var sortedListToBST = function (head) {
    const nums = [];
    for (; head; head = head.next) {
        nums.push(head.val);
    }
    const dfs = (i, j) => {
        if (i > j) {
            return null;
        }
        const mid = (i + j) >> 1;
        const left = dfs(i, mid - 1);
        const right = dfs(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
    };
    return dfs(0, nums.length - 1);
};
