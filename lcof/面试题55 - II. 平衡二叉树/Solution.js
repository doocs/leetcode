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
  if (!root) return true;
  if (!isBalanced(root.left) || !isBalanced(root.right)) return false;
  if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) return false;
  return true;
};

function getDepth(node) {
  if (!node) return 0;
  return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
}
