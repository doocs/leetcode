/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} k
 * @return {number}
 */
var kthLargest = function (root, k) {
    const inorder = root => {
        if (!root) {
            return;
        }
        inorder(root.right);
        --cur;
        if (cur == 0) {
            res = root.val;
            return;
        }
        inorder(root.left);
    };
    let res = 0;
    let cur = k;
    inorder(root);
    return res;
};
