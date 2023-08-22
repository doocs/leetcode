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

function minimumFlips(root: TreeNode | null, result: boolean): number {
    const dfs = (root: TreeNode | null): [number, number] => {
        if (!root) {
            return [1 << 30, 1 << 30];
        }
        const x = root.val;
        if (x < 2) {
            return [x, x ^ 1];
        }
        const [l0, l1] = dfs(root.left);
        const [r0, r1] = dfs(root.right);
        if (x === 2) {
            return [l0 + r0, Math.min(l0 + r1, l1 + r0, l1 + r1)];
        }
        if (x === 3) {
            return [Math.min(l0 + r0, l0 + r1, l1 + r0), l1 + r1];
        }
        if (x === 4) {
            return [Math.min(l0 + r0, l1 + r1), Math.min(l0 + r1, l1 + r0)];
        }
        return [Math.min(l1, r1), Math.min(l0, r0)];
    };
    return dfs(root)[result ? 1 : 0];
}
