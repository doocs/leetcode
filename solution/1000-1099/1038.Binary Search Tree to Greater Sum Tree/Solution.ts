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

function bstToGst(root: TreeNode | null): TreeNode | null {
    let cur = root;
    let sum = 0;
    while (cur != null) {
        const { val, left, right } = cur;
        if (right == null) {
            sum += val;
            cur.val = sum;
            cur = left;
        } else {
            let next = right;
            while (next.left != null && next.left != cur) {
                next = next.left;
            }
            if (next.left == null) {
                next.left = cur;
                cur = right;
            } else {
                next.left = null;
                sum += val;
                cur.val = sum;
                cur = left;
            }
        }
    }
    return root;
}
