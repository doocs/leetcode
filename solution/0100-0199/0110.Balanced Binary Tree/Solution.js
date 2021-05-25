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
 * @return {boolean}
 */
 var isBalanced = function(root) {
    if (root == null) return true; 
    let left = root.left;
    let right = root.right;
    return isBalanced(left) && isBalanced(right) && Math.abs(depth(left) - depth(right)) <= 1;
};

function depth(root) {
    if (root == null) return 0;
    return Math.max(depth(root.left), depth(root.right)) + 1;
}