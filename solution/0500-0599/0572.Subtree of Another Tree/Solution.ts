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

const dfs = (root: TreeNode | null, subRoot: TreeNode | null) => {
    if (root == null && subRoot == null) {
        return true;
    }
    if (root == null || subRoot == null || root.val !== subRoot.val) {
        return false;
    }
    return dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right);
};

function isSubtree(root: TreeNode | null, subRoot: TreeNode | null): boolean {
    if (root == null) {
        return false;
    }
    return (
        dfs(root, subRoot) ||
        isSubtree(root.left, subRoot) ||
        isSubtree(root.right, subRoot)
    );
}
