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
 * @return {number[]}
 */
var averageOfLevels = function (root) {
    let s = [];
    let cnt = [];
    function dfs(root, i) {
        if (!root) {
            return;
        }
        if (s.length == i) {
            s.push(root.val);
            cnt.push(1);
        } else {
            s[i] += root.val;
            cnt[i]++;
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
    dfs(root, 0);
    let ans = [];
    for (let i = 0; i < s.length; ++i) {
        ans.push(s[i] / cnt[i]);
    }
    return ans;
};
