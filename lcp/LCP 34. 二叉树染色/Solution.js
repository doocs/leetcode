/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} k
 * @return {number}
 */
var maxValue = function (root, k) {
    const dfs = root => {
        const ans = Array(k + 1).fill(0);
        if (!root) {
            return ans;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        ans[0] = Math.max(...l) + Math.max(...r);
        for (let i = 0; i < k; i++) {
            for (let j = 0; j < k - i; ++j) {
                ans[i + j + 1] = Math.max(ans[i + j + 1], l[i] + r[j] + root.val);
            }
        }
        return ans;
    };
    return Math.max(...dfs(root));
};
