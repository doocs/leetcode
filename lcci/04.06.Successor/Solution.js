/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @return {TreeNode}
 */
var inorderSuccessor = function (root, p) {
    if (root == null) {
        return root;
    }
    const { val, left, right } = root;
    const res = inorderSuccessor(left, p);
    if (res != null) {
        return res;
    }
    if (val > p.val) {
        return root;
    }
    return inorderSuccessor(right, p);
};
