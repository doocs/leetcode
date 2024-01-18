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

function postorderTraversal(root: TreeNode | null): number[] {
    const res = [];
    while (root != null) {
        const { val, left, right } = root;
        if (right == null) {
            res.push(val);
            root = left;
        } else {
            let next = right;
            while (next.left != null && next.left != root) {
                next = next.left;
            }
            if (next.left == null) {
                res.push(val);
                next.left = root;
                root = right;
            } else {
                next.left = null;
                root = left;
            }
        }
    }
    return res.reverse();
}
