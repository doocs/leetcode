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
var inorderTraversal = function (root) {
    let ans = [],
        stk = [];
    while (root || stk.length > 0) {
        if (root) {
            stk.push(root);
            root = root.left;
        } else {
            root = stk.pop();
            ans.push(root.val);
            root = root.right;
        }
    }
    return ans;
};
