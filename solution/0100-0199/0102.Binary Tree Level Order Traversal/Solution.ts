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
    const queue = [root];
    while (queue.length != 0) {
        const n = queue.length;
        res.push(
            new Array(n).fill(null).map(() => {
                const { val, left, right } = queue.shift();
                left && queue.push(left);
                right && queue.push(right);
                return val;
            }),
        );
    }
    return res;
}
