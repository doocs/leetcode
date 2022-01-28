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

function isSubStructure(A: TreeNode | null, B: TreeNode | null): boolean {
    if (A == null || B == null) {
        return false;
    }
    if (A.val == B.val && exam(A.left, B.left) && exam(A.right, B.right)) {
        return true;
    }
    return isSubStructure(A.left, B) || isSubStructure(A.right, B);
}

function exam(A: TreeNode | null, B: TreeNode | null) {
    if (B == null) {
        return true;
    }
    if (A == null) {
        return false;
    }
    return A.val === B.val && exam(A.left, B.left) && exam(A.right, B.right);
}
