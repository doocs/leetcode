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
  function dfs(a, b) {
    if (!a && !b) return true;
    if (!a || !b) return false;
    return a.val === b.val && dfs(a.left, b.right) && dfs(a.right, b.left);
  }
  if (!root) return true;
  return dfs(root.left, root.right);
};
