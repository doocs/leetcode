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
 var inorderTraversal = function(root) {
    let res = [];
    let s = [];
    while (root || s.length > 0) {
        if (root) {
            s.push(root);
            root = root.left;
        } else {
            root = s.pop();
            res.push(root.val);
            root = root.right;
        }
    }
    return res;
};