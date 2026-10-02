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

function maxDepth(root: TreeNode | null): number {
    if (root === null) return 0;
    const stack: [TreeNode, number][] = [[root, 1]];
    let depth = 0;
    while (stack.length > 0) {
        const [node, level] = stack.pop()!;
        depth = Math.max(depth, level);
        if (node.left !== null) stack.push([node.left, level + 1]);
        if (node.right !== null) stack.push([node.right, level + 1]);
    }
    return depth;
}
