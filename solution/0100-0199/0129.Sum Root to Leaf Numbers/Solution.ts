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
    function dfs(root: TreeNode | null, s: number): number {
        if (!root) return 0;
        s = s * 10 + root.val;
        if (!root.left && !root.right) return s;
        return dfs(root.left, s) + dfs(root.right, s);
    }
    return dfs(root, 0);
}
