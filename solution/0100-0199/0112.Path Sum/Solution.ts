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

function hasPathSum(root: TreeNode | null, targetSum: number): boolean {
    if (root == null) {
        return false;
    }
    const { val, left, right } = root;
    if (left == null && right == null) {
        return targetSum - val === 0;
    }
    return (
        hasPathSum(left, targetSum - val) || hasPathSum(right, targetSum - val)
    );
}
