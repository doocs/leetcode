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
    const dfs = root => {
        if (!root) {
            return 0;
        }
        let [l, r] = [dfs(root.left), dfs(root.right)];
        l = root.left && root.left.val === root.val ? l + 1 : 0;
        r = root.right && root.right.val === root.val ? r + 1 : 0;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    };
    dfs(root);
    return ans;
};
