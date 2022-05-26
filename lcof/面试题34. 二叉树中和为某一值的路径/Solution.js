/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} sum
 * @return {number[][]}
 */
var pathSum = function (root, sum) {
    if (!root) return [];
    let res = [];
    function dfs(node, sum, arr) {
        if (!node) return;
        arr = [...arr, node.val];
        if (node.val === sum && !node.left && !node.right) {
            res.push(arr);
            return;
        }
        dfs(node.left, sum - node.val, arr);
        dfs(node.right, sum - node.val, arr);
    }
    dfs(root, sum, []);
    return res;
};
