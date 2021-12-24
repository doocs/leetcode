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
type TreeNodeOptional = TreeNode | null;
const isSame = (a: TreeNodeOptional, b: TreeNodeOptional): boolean => {
    if (!a && !b) return true;
    if (!a || !b) return false;
    return (
        a.val === b.val && isSame(a.left, b.right) && isSame(a.right, b.left)
    );
};
var isSymmetric = function (root: TreeNode | null): boolean {
    if (!root) return true;
    return isSame(root.left, root.right);
};
