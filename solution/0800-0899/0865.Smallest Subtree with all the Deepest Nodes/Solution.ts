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

function subtreeWithAllDeepest(root: TreeNode | null): TreeNode | null {
    const dfs = (root: TreeNode | null): [TreeNode, number] => {
        if (!root) {
            return [null, 0];
        }
        const [l, ld] = dfs(root.left);
        const [r, rd] = dfs(root.right);
        if (ld > rd) {
            return [l, ld + 1];
        }
        if (ld < rd) {
            return [r, rd + 1];
        }
        return [root, ld + 1];
    };
    return dfs(root)[0];
}
