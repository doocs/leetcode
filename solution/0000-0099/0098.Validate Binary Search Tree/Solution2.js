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
 * @return {boolean}
 */
var isValidBST = function (root) {
    function dfs(root, l, r) {
        if (!root) {
            return true;
        }
        if (root.val <= l || root.val >= r) {
            return false;
        }
        return dfs(root.left, l, root.val) && dfs(root.right, root.val, r);
    }
    return dfs(root, -Infinity, Infinity);
};
