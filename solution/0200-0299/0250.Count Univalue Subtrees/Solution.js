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
    const dfs = root => {
        if (!root) {
            return true;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        if (!l || !r) {
            return false;
        }
        if (root.left && root.left.val !== root.val) {
            return false;
        }
        if (root.right && root.right.val !== root.val) {
            return false;
        }
        ++ans;
        return true;
    };
    dfs(root);
    return ans;
};
