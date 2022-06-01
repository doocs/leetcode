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

function checkSubTree(t1: TreeNode | null, t2: TreeNode | null): boolean {
    if (t1 == null && t2 == null) {
        return true;
    }
    if (t1 == null || t2 == null) {
        return false;
    }
    if (t1.val === t2.val) {
        return (
            checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right)
        );
    }
    return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
}
