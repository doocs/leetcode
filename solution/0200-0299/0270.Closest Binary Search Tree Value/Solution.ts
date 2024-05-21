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

function closestValue(root: TreeNode | null, target: number): number {
    if (!root) return Number.NaN;

    let res = 0;
    let diff = Number.POSITIVE_INFINITY;

    const dfs = (node: TreeNode | null): undefined => {
        if (!node) return;

        const next = Math.abs(target - node.val);

        if (next < diff || (next === diff && node.val < res)) {
            diff = next;
            res = node.val;
        }

        node = target < node.val ? node.left : node.right;

        return dfs(node);
    };

    dfs(root);

    return res;
}
