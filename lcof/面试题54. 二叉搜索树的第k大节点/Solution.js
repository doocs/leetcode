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
  let res;
  let t = 0;
  function traversal(node) {
    if (!node) return;
    traversal(node.right);
    if (++t === k) res = node.val;
    traversal(node.left);
  }
  traversal(root);
  return res;
};
