/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function sumNumbers(root: TreeNode | null): number {
    return dfs(root);
}

function dfs(root: TreeNode | null, preSum: number = 0): number {
    if (!root) return 0;
    preSum = preSum * 10 + root.val;
    if (!root.left && !root.right) return preSum;
    return dfs(root.left, preSum) + dfs(root.right, preSum);
}
