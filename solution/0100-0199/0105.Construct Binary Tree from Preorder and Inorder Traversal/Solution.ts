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

function buildTree(preorder: number[], inorder: number[]): TreeNode | null {
    const n = preorder.length;
    if (n === 0) {
        return null;
    }
    const val = preorder[0];
    const index = inorder.indexOf(val);
    return new TreeNode(
        val,
        buildTree(preorder.slice(1, index + 1), inorder.slice(0, index)),
        buildTree(preorder.slice(index + 1), inorder.slice(index + 1)),
    );
}
