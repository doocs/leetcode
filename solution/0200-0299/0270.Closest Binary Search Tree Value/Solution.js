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
 var closestValue = function(root, target) {
    let res = root.val;
    let minDiff = Math.abs(root.val - target);
    while (root) {
        const val = Math.abs(root.val - target);
        if (minDiff > val) {
            minDiff = val;
            res = root.val;
        }
        if (root.val > target) {
            root = root.left;
        } else {
            root = root.right;
        }
    }
    return res;
};