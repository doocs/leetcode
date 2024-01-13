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

function isSameTree(p: TreeNode | null, q: TreeNode | null): boolean {
    const queue = [];
    p && queue.push(p);
    q && queue.push(q);
    if (queue.length === 1) {
        return false;
    }
    while (queue.length !== 0) {
        const node1 = queue.shift();
        const node2 = queue.shift();
        if (node1.val !== node2.val) {
            return false;
        }
        if (
            (node1.left == null && node2.left != null) ||
            (node1.left != null && node2.left == null)
        ) {
            return false;
        }
        if (
            (node1.right == null && node2.right != null) ||
            (node1.right != null && node2.right == null)
        ) {
            return false;
        }

        if (node1.left != null) {
            queue.push(node1.left);
            queue.push(node2.left);
        }
        if (node1.right != null) {
            queue.push(node1.right);
            queue.push(node2.right);
        }
    }
    return true;
}
