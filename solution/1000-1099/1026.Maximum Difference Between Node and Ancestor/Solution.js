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
var maxAncestorDiff = function (root) {
    let ans = 0;
    const dfs = (root, mi, mx) => {
        if (!root) {
            return;
        }
        ans = Math.max(ans, Math.abs(mi - root.val), Math.abs(mx - root.val));
        mi = Math.min(mi, root.val);
        mx = Math.max(mx, root.val);
        dfs(root.left, mi, mx);
        dfs(root.right, mi, mx);
    };
    dfs(root, root.val, root.val);
    return ans;
};
