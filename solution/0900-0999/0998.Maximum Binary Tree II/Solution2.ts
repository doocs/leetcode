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

function insertIntoMaxTree(root: TreeNode | null, val: number): TreeNode | null {
    if (root.val < val) {
        return new TreeNode(val, root);
    }
    const node = new TreeNode(val);
    let curr = root;
    while (curr.right && curr.right.val > val) {
        curr = curr.right;
    }
    node.left = curr.right;
    curr.right = node;
    return root;
}
