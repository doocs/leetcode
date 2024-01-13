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
    if (inorder.length === 0) {
        return null;
    }
    const val = preorder[0];
    const i = inorder.indexOf(val);
    return new TreeNode(
        val,
        buildTree(preorder.slice(1, i + 1), inorder.slice(0, i)),
        buildTree(preorder.slice(i + 1), inorder.slice(i + 1)),
    );
}
