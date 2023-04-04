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

function countUnivalSubtrees(root: TreeNode | null): number {
    let ans: number = 0;
    const dfs = (root: TreeNode | null): boolean => {
        if (root == null) {
            return true;
        }
        const l: boolean = dfs(root.left);
        const r: boolean = dfs(root.right);
        if (!l || !r) {
            return false;
        }
        if (root.left != null && root.left.val != root.val) {
            return false;
        }
        if (root.right != null && root.right.val != root.val) {
            return false;
        }
        ++ans;
        return true;
    };
    dfs(root);
    return ans;
}
