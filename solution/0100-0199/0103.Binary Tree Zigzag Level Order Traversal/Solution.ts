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

function zigzagLevelOrder(root: TreeNode | null): number[][] {
    const res = [];
    if (root == null) {
        return res;
    }
    let isDesc = false;
    const queue = [root];
    while (queue.length !== 0) {
        const arr = queue.slice().map(() => {
            const { val, left, right } = queue.shift();
            left && queue.push(left);
            right && queue.push(right);
            return val;
        });
        res.push(isDesc ? arr.reverse() : arr);
        isDesc = !isDesc;
    }
    return res;
}
