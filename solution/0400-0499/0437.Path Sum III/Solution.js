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
 * @param {number} targetSum
 * @return {number}
 */
var pathSum = function (root, targetSum) {
    const cnt = new Map();
    const dfs = (node, s) => {
        if (!node) {
            return 0;
        }
        s += node.val;
        let ans = cnt.get(s - targetSum) || 0;
        cnt.set(s, (cnt.get(s) || 0) + 1);
        ans += dfs(node.left, s);
        ans += dfs(node.right, s);
        cnt.set(s, cnt.get(s) - 1);
        return ans;
    };
    cnt.set(0, 1);
    return dfs(root, 0);
};
