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

function lcaDeepestLeaves(root: TreeNode | null): TreeNode | null {
    const dfs = (root: TreeNode | null): [TreeNode | null, number] => {
        if (root === null) {
            return [null, 0];
        }
        const [l, d1] = dfs(root.left);
        const [r, d2] = dfs(root.right);
        if (d1 > d2) {
            return [l, d1 + 1];
        }
        if (d1 < d2) {
            return [r, d2 + 1];
        }
        return [root, d1 + 1];
    };
    return dfs(root)[0];
}
