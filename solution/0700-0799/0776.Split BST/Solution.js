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
 * @return {TreeNode[]}
 */
var splitBST = function (root, target) {
    let ans = [null, null];
    if (!root) {
        return ans;
    }
    if (root.val <= target) {
        ans = splitBST(root.right, target);
        root.right = ans[0];
        ans[0] = root;
    } else {
        ans = splitBST(root.left, target);
        root.left = ans[1];
        ans[1] = root;
    }
    return ans;
};
