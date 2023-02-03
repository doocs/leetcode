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

function btreeGameWinningMove(
    root: TreeNode | null,
    n: number,
    x: number,
): boolean {
    const dfs = (root: TreeNode | null): TreeNode | null => {
        if (!root || root.val === x) {
            return root;
        }
        return dfs(root.left) || dfs(root.right);
    };

    const count = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    };

    const node = dfs(root);
    const l = count(node.left);
    const r = count(node.right);
    return Math.max(l, r, n - l - r - 1) > n / 2;
}
