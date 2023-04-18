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

function maxAncestorDiff(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null, mi: number, mx: number): void => {
        if (!root) {
            return;
        }
        ans = Math.max(ans, Math.abs(root.val - mi), Math.abs(root.val - mx));
        mi = Math.min(mi, root.val);
        mx = Math.max(mx, root.val);
        dfs(root.left, mi, mx);
        dfs(root.right, mi, mx);
    };
    let ans: number = 0;
    dfs(root, root.val, root.val);
    return ans;
}
