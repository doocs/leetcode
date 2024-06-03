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

function findLeaves(root: TreeNode | null): number[][] {
    const ans: number[][] = [];
    const dfs = (root: TreeNode | null): number => {
        if (root === null) {
            return 0;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        const h = Math.max(l, r);
        if (ans.length === h) {
            ans.push([]);
        }
        ans[h].push(root.val);
        return h + 1;
    };
    dfs(root);
    return ans;
}
