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

function buildTree(inorder: number[], postorder: number[]): TreeNode | null {
    const n = postorder.length;
    if (n == 0) {
        return null;
    }
    const val = postorder[n - 1];
    const index = inorder.indexOf(val);
    return new TreeNode(
        val,
        buildTree(inorder.slice(0, index), postorder.slice(0, index)),
        buildTree(inorder.slice(index + 1), postorder.slice(index, n - 1)),
    );
}
