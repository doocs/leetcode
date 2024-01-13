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

function findTarget(root: TreeNode | null, k: number): boolean {
    const q = [root];
    const vis = new Set<number>();
    while (q.length) {
        for (let n = q.length; n; --n) {
            const { val, left, right } = q.shift();
            if (vis.has(k - val)) {
                return true;
            }
            vis.add(val);
            left && q.push(left);
            right && q.push(right);
        }
    }
    return false;
}
