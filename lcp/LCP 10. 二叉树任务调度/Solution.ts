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

function minimalExecTime(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null): [number, number] => {
        if (root === null) {
            return [0, 0];
        }
        const [s1, t1] = dfs(root.left);
        const [s2, t2] = dfs(root.right);
        const s = s1 + s2 + root.val;
        const t = Math.max(t1, t2, (s1 + s2) / 2) + root.val;
        return [s, t];
    };
    return dfs(root)[1];
}
