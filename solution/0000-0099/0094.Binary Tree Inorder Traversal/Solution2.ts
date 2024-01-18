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
    const stack = [];
    while (root != null || stack.length != 0) {
        if (root != null) {
            stack.push(root);
            root = root.left;
        } else {
            const { val, right } = stack.pop();
            res.push(val);
            root = right;
        }
    }
    return res;
}
