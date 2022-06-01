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

function dfs(root: TreeNode | null, sum: number): number {
    let res = 0;
    if (root == null) {
        return res;
    }
    sum -= root.val;
    if (sum === 0) {
        res++;
    }
    return res + dfs(root.left, sum) + dfs(root.right, sum);
}

function pathSum(root: TreeNode | null, sum: number): number {
    if (root == null) {
        return 0;
    }
    return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
}
