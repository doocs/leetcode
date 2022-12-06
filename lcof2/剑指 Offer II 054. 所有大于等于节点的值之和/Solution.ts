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

function convertBST(root: TreeNode | null): TreeNode | null {
    let sum = 0;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return;
        }
        dfs(root.right);
        root.val += sum;
        sum = root.val;
        dfs(root.left);
    };
    dfs(root);
    return root;
}
