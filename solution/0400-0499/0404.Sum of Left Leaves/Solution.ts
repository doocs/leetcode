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

function sumOfLeftLeaves(root: TreeNode | null): number {
    if (!root) {
        return 0;
    }
    let ans = sumOfLeftLeaves(root.right);
    if (root.left) {
        if (root.left.left === root.left.right) {
            ans += root.left.val;
        } else {
            ans += sumOfLeftLeaves(root.left);
        }
    }
    return ans;
}
