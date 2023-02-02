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
 * @param {number} target
 * @return {number[][]}
 */
var pathSum = function (root, target) {
    const ans = [];
    const t = [];
    const dfs = (root, s) => {
        if (!root) {
            return;
        }
        t.push(root.val);
        s -= root.val;
        if (!root.left && !root.right && !s) {
            ans.push([...t]);
        }
        dfs(root.left, s);
        dfs(root.right, s);
        t.pop();
    };
    dfs(root, target);
    return ans;
};
