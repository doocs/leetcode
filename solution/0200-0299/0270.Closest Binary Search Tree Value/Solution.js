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
 * @return {number}
 */
var closestValue = function (root, target) {
    let mi = Infinity;
    let ans = root.val;
    const dfs = root => {
        if (!root) {
            return;
        }
        dfs(root.left);
        const t = Math.abs(root.val - target);
        if (t < mi) {
            mi = t;
            ans = root.val;
        }
        dfs(root.right);
    };
    dfs(root);
    return ans;
};
