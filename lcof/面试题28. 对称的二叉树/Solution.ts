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
    const dfs = (a: TreeNode | null, b: TreeNode | null): boolean => {
        if (!a && !b) {
            return true;
        }
        if (!a || !b || a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
    };
    return dfs(root, root);
}
