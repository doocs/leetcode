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

function maxSumBST(root: TreeNode | null): number {
    const inf = 1 << 30;
    let ans = 0;
    const dfs = (root: TreeNode | null): [boolean, number, number, number] => {
        if (!root) {
            return [true, inf, -inf, 0];
        }
        const [lbst, lmi, lmx, ls] = dfs(root.left);
        const [rbst, rmi, rmx, rs] = dfs(root.right);
        if (lbst && rbst && lmx < root.val && root.val < rmi) {
            const s = ls + rs + root.val;
            ans = Math.max(ans, s);
            return [true, Math.min(lmi, root.val), Math.max(rmx, root.val), s];
        }
        return [false, 0, 0, 0];
    };
    dfs(root);
    return ans;
}
