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
var findSecondMinimumValue = function (root) {
    let ans = -1;
    const v = root.val;
    function dfs(root) {
        if (!root) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        if (root.val > v) {
            if (ans == -1 || ans > root.val) {
                ans = root.val;
            }
        }
    }
    dfs(root);
    return ans;
};
