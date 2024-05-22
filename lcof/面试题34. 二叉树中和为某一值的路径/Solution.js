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
        const { val, left, right } = root;
        t.push(val);
        s -= val;
        if (!left && !right && !s) {
            ans.push([...t]);
        }
        dfs(left, s);
        dfs(right, s);
        t.pop();
    };
    dfs(root, target);
    return ans;
};
