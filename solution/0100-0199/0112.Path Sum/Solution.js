/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} targetSum
 * @return {boolean}
 */
var hasPathSum = function (root, targetSum) {
    function dfs(root, s) {
        if (!root) return false;
        s += root.val;
        if (!root.left && !root.right && s == targetSum) return true;
        return dfs(root.left, s) || dfs(root.right, s);
    }
    return dfs(root, 0);
};
