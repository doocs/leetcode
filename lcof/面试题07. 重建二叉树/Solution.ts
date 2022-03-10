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
    if (preorder.length == 0) return null;
    let val: number = preorder[0];
    let node: TreeNode = new TreeNode(val);
    let index: number = inorder.indexOf(val);
    node.left = buildTree(
        preorder.slice(1, index + 1),
        inorder.slice(0, index),
    );
    node.right = buildTree(preorder.slice(index + 1), inorder.slice(index + 1));
    return node;
}
