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

function countDominantNodes(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (node: TreeNode | null): number => {
        if (!node) {
            return -Infinity;
        }
        const l = dfs(node.left);
        const r = dfs(node.right);
        const mx = Math.max(l, r, node.val);
        if (mx === node.val) {
            ++ans;
        }
        return mx;
    };
    dfs(root);
    return ans;
}
