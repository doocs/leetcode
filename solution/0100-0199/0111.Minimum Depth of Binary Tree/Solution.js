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
 * @return {number}
 */
var minDepth = function (root) {
    function dfs(root) {
        if (!root) return 0;
        if (!root.left) return 1 + dfs(root.right);
        if (!root.right) return 1 + dfs(root.left);
        return 1 + Math.min(dfs(root.left), dfs(root.right));
    }
    return dfs(root);
};
