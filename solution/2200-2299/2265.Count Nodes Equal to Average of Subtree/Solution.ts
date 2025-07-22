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

function averageOfSubtree(root: TreeNode | null): number {
    let ans: number = 0;
    const dfs = (root: TreeNode | null): [number, number] => {
        if (!root) {
            return [0, 0];
        }
        const [ls, ln] = dfs(root.left);
        const [rs, rn] = dfs(root.right);
        const s = ls + rs + root.val;
        const n = ln + rn + 1;
        if (Math.floor(s / n) === root.val) {
            ++ans;
        }
        return [s, n];
    };
    dfs(root);
    return ans;
}
