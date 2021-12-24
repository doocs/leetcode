/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isBalanced = function (root) {
    const depth = root => {
        if (!root) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    };

    if (!root) {
        return true;
    }
    return (
        Math.abs(depth(root.left) - depth(root.right)) <= 1 &&
        isBalanced(root.left) &&
        isBalanced(root.right)
    );
};
