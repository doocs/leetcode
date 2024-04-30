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

function constructFromPrePost(preorder: number[], postorder: number[]): TreeNode | null {
    const pos: Map<number, number> = new Map();
    const n = postorder.length;
    for (let i = 0; i < n; ++i) {
        pos.set(postorder[i], i);
    }
    const dfs = (i: number, j: number, n: number): TreeNode | null => {
        if (n <= 0) {
            return null;
        }
        const root = new TreeNode(preorder[i]);
        if (n === 1) {
            return root;
        }
        const k = pos.get(preorder[i + 1])!;
        const m = k - j + 1;
        root.left = dfs(i + 1, j, m);
        root.right = dfs(i + 1 + m, k + 1, n - 1 - m);
        return root;
    };
    return dfs(0, 0, n);
}
