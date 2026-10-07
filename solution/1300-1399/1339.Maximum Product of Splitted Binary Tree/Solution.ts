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

function maxProduct(root: TreeNode | null): number {
    const sum = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
    };
    const s = sum(root);
    let ans = 0n;
    const mod = 1000000007n;
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        const t = root.val + dfs(root.left) + dfs(root.right);
        if (t < s) {
            const prod = BigInt(t) * BigInt(s - t);
            if (prod > ans) {
                ans = prod;
            }
        }
        return t;
    };
    dfs(root);
    return Number(ans % mod);
}
