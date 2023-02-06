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

function evaluateTree(root: TreeNode | null): boolean {
    const { val, left, right } = root;
    if (left == null) {
        return val === 1;
    }
    if (val === 2) {
        return evaluateTree(left) || evaluateTree(right);
    }
    return evaluateTree(left) && evaluateTree(right);
}
