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

function pathSum(root: TreeNode | null, sum: number): number {
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    const dfs = (root: TreeNode | null, s: number): number => {
        if (!root) {
            return 0;
        }
        s += root.val;
        let ans = cnt.get(s - sum) ?? 0;
        cnt.set(s, (cnt.get(s) ?? 0) + 1);
        ans += dfs(root.left, s);
        ans += dfs(root.right, s);
        cnt.set(s, (cnt.get(s) ?? 0) - 1);
        return ans;
    };
    return dfs(root, 0);
}
