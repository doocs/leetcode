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

function minDepth(root: TreeNode | null): number {
    if (root == null) {
        return 0;
    }
    const queue = [root];
    let head = 0;
    let depth = 1;
    while (head < queue.length) {
        const end = queue.length;
        for (; head < end; ++head) {
            const node = queue[head];
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left) {
                queue.push(node.left);
            }
            if (node.right) {
                queue.push(node.right);
            }
        }
        ++depth;
    }
    return 0;
}
