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

function addOneRow(
    root: TreeNode | null,
    val: number,
    depth: number,
): TreeNode | null {
    if (depth === 1) {
        return new TreeNode(val, root);
    }

    const queue = [root];
    for (let i = 1; i < depth - 1; i++) {
        const n = queue.length;
        for (let j = 0; j < n; j++) {
            const { left, right } = queue.shift();
            left && queue.push(left);
            right && queue.push(right);
        }
    }
    for (const node of queue) {
        node.left = new TreeNode(val, node.left);
        node.right = new TreeNode(val, null, node.right);
    }
    return root;
}
