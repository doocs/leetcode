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

function largestValues(root: TreeNode | null): number[] {
    const res: number[] = [];
    const queue: TreeNode[] = [];
    if (root) {
        queue.push(root);
    }
    while (queue.length) {
        const n = queue.length;
        let max = -Infinity;
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            max = Math.max(max, val);
            left && queue.push(left);
            right && queue.push(right);
        }
        res.push(max);
    }
    return res;
}
