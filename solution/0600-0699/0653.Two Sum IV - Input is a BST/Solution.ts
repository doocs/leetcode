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
    let nodes: Set<number> = new Set();
    return find(root, k, nodes);
}

function find(root: TreeNode | null, k: number, nodes: Set<number>): boolean {
    if (!root) return false;
    if (nodes.has(k - root.val)) return true;
    nodes.add(root.val);
    return find(root.left, k, nodes) || find(root.right, k, nodes);
}
