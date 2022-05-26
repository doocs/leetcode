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
var longestUnivaluePath = function (root) {
    let ans = 0;
    let dfs = function (root) {
        if (!root) {
            return 0;
        }
        let left = dfs(root.left),
            right = dfs(root.right);
        left = root.left?.val == root.val ? left + 1 : 0;
        right = root.right?.val == root.val ? right + 1 : 0;
        ans = Math.max(ans, left + right);
        return Math.max(left, right);
    };
    dfs(root);
    return ans;
};
