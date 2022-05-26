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
var isSymmetric = function (root) {
  if (!root) return true;
  return checkSymmetric(root.left, root.right);
};

const checkSymmetric = function (leftNode, rightNode) {
  if (!leftNode && !rightNode) return true;
  if (!leftNode || !rightNode) return false;
  return (
    leftNode.val === rightNode.val &&
    checkSymmetric(leftNode.left, rightNode.right) &&
    checkSymmetric(rightNode.left, leftNode.right)
  );
};
