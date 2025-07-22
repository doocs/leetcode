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

function longestUnivaluePath(root: TreeNode | null): number {
    let ans: number = 0;
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        let [l, r] = [dfs(root.left), dfs(root.right)];
        l = root.left && root.left.val === root.val ? l + 1 : 0;
        r = root.right && root.right.val === root.val ? r + 1 : 0;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    };
    dfs(root);
    return ans;
}
