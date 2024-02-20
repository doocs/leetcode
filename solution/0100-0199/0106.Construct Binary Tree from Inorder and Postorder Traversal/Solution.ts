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

function buildTree(inorder: number[], postorder: number[]): TreeNode | null {
    const n = inorder.length;
    const d: Record<number, number> = {};
    for (let i = 0; i < n; i++) {
        d[inorder[i]] = i;
    }
    const dfs = (i: number, j: number, n: number): TreeNode | null => {
        if (n <= 0) {
            return null;
        }
        const v = postorder[j + n - 1];
        const k = d[v];
        const l = dfs(i, j, k - i);
        const r = dfs(k + 1, j + k - i, n - 1 - (k - i));
        return new TreeNode(v, l, r);
    };
    return dfs(0, 0, n);
}
