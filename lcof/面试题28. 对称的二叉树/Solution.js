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
    const dfs = (a, b) => {
        if (!a && !b) {
            return true;
        }
        if (!a || !b || a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
    };
    return dfs(root, root);
};
