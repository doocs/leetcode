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

function isValidBST(root: TreeNode | null): boolean {
    if (root == null) {
        return true;
    }
    const { val, left, right } = root;
    const dfs = (root: TreeNode | null, min: number, max: number) => {
        if (root == null) {
            return true;
        }
        const { val, left, right } = root;
        if (val <= min || val >= max) {
            return false;
        }
        return dfs(left, min, Math.min(val, max)) && dfs(right, Math.max(val, min), max);
    };
    return dfs(left, -Infinity, val) && dfs(right, val, Infinity);
}
