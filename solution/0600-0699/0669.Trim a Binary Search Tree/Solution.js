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
 * @param {number} low
 * @param {number} high
 * @return {TreeNode}
 */
var trimBST = function (root, low, high) {
    function dfs(root) {
        if (!root) {
            return root;
        }
        if (root.val < low) {
            return dfs(root.right);
        }
        if (root.val > high) {
            return dfs(root.left);
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        return root;
    }
    return dfs(root);
};
