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

function sumRootToLeaf(root: TreeNode | null): number {
    const dfs = (node: TreeNode | null, x: number): number => {
        if (node === null) {
            return 0;
        }

        x = (x << 1) | node.val;

        if (node.left === null && node.right === null) {
            return x;
        }

        return dfs(node.left, x) + dfs(node.right, x);
    };

    return dfs(root, 0);
}
