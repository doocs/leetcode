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

function levelOrder(root: TreeNode | null): number[][] {
    const res = [];
    if (root == null) {
        return res;
    }
    let isEven = false;
    const queue = [root];
    while (queue.length !== 0) {
        const n = queue.length;
        const vals = new Array(n);
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            vals[i] = val;
            left && queue.push(left);
            right && queue.push(right);
        }
        res.push(isEven ? vals.reverse() : vals);
        isEven = !isEven;
    }
    return res;
}
