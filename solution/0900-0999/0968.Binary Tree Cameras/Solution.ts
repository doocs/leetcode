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

function minCameraCover(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null): number[] => {
        if (!root) {
            return [1 << 29, 0, 0];
        }
        const [la, lb, lc] = dfs(root.left);
        const [ra, rb, rc] = dfs(root.right);
        const a = 1 + Math.min(la, lb, lc) + Math.min(ra, rb, rc);
        const b = Math.min(la + ra, la + rb, lb + ra);
        const c = lb + rb;
        return [a, b, c];
    };
    const [a, b, _] = dfs(root);
    return Math.min(a, b);
}
