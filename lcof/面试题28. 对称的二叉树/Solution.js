/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isSymmetric = function (root) {
    function dfs(left, right) {
        if (!left && !right) return true;
        if (!left || !right || left.val != right.val) return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
    if (!root) return true;
    return dfs(root.left, root.right);
};
