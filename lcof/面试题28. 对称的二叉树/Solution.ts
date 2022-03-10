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

function isSymmetric(root: TreeNode | null): boolean {
    if (root == null) {
        return true;
    }
    const dfs = (l: TreeNode | null, r: TreeNode | null) => {
        if (l == null && r == null) {
            return true;
        }
        if (l == null || r == null) {
            return false;
        }
        return (
            l.val == r.val &&
            dfs(l.left, r.right) &&
            dfs(l.right, r.left) &&
            true
        );
    };
    return dfs(root.left, root.right);
}
