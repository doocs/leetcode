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
    let pre = -Infinity;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return true;
        }
        const { val, left, right } = root;
        if (!dfs(left) || val <= pre) {
            return false;
        }
        pre = val;
        return dfs(right);
    };
    return dfs(root);
}
