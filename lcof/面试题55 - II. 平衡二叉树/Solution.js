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
var isBalanced = function (root) {
    const dfs = root => {
        if (!root) {
            return 0;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        if (l === -1 || r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return 1 + Math.max(l, r);
    };
    return dfs(root) !== -1;
};
