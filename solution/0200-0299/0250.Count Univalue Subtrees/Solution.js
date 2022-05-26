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
var countUnivalSubtrees = function (root) {
    let ans = 0;
    let dfs = function (root) {
        if (!root) {
            return true;
        }
        const left = dfs(root.left),
            right = dfs(root.right);
        let t = true;
        if (root.left && root.left.val != root.val) {
            t = false;
        }
        if (root.right && root.right.val != root.val) {
            t = false;
        }
        if (left && t && right) {
            ++ans;
        }
        return left && t && right;
    };
    dfs(root);
    return ans;
};
