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
 * @return {TreeNode}
 */
var invertTree = function (root) {
    function dfs(root) {
        if (!root) return;
        [root.left, root.right] = [root.right, root.left];
        dfs(root.left);
        dfs(root.right);
    }
    dfs(root);
    return root;
};
