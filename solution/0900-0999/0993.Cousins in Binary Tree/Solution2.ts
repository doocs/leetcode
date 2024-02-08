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

function isCousins(root: TreeNode | null, x: number, y: number): boolean {
    let [d1, d2, p1, p2] = [0, 0, null, null];
    const dfs = (root: TreeNode | null, parent: TreeNode | null, depth: number) => {
        if (!root) {
            return;
        }
        if (root.val === x) {
            [d1, p1] = [depth, parent];
        } else if (root.val === y) {
            [d2, p2] = [depth, parent];
        }
        dfs(root.left, root, depth + 1);
        dfs(root.right, root, depth + 1);
    };
    dfs(root, null, 0);
    return d1 === d2 && p1 !== p2;
}
