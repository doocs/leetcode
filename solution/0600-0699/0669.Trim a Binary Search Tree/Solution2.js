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
 * @param {number} low
 * @param {number} high
 * @return {TreeNode}
 */
var trimBST = function (root, low, high) {
    while (root && (root.val < low || root.val > high)) {
        root = root.val < low ? root.right : root.left;
    }
    if (!root) {
        return root;
    }
    let node = root;
    while (node.left) {
        if (node.left.val < low) {
            node.left = node.left.right;
        } else {
            node = node.left;
        }
    }
    node = root;
    while (node.right) {
        if (node.right.val > high) {
            node.right = node.right.left;
        } else {
            node = node.right;
        }
    }
    return root;
};
