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

function isUnivalTree(root: TreeNode | null): boolean {
    const val = root.val;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return true;
        }
        return root.val === val && dfs(root.left) && dfs(root.right);
    };
    return dfs(root.left) && dfs(root.right);
}
