/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var mirrorTree = function (root) {
    if (!root) {
        return null;
    }
    const { left, right } = root;
    root.left = right;
    root.right = left;
    mirrorTree(left);
    mirrorTree(right);
    return root;
};
