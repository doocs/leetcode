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

function inorderTraversal(root: TreeNode | null): number[] {
    const res = [];
    while (root != null) {
        const { val, left, right } = root;
        if (left == null) {
            res.push(val);
            root = right;
        } else {
            let mostRight = left;
            while (mostRight.right != null && mostRight.right != root) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == root) {
                res.push(val);
                mostRight.right = null;
                root = right;
            } else {
                mostRight.right = root;
                root = left;
            }
        }
    }
    return res;
}
