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
var invertTree = function (root) {
  if (!root) return null;
  [root.left, root.right] = [root.right, root.left];
  invertTree(root.left);
  invertTree(root.right);
  return root;
};

//non-recursion
var invertTree = function (root) {
  if (!root) {
    return null;
  }
  let q = [];
  q.push(root);
  while (q.length) {
    let cur = q.pop();
    [cur.left, cur.right] = [cur.right, cur.left];
    if (cur.left) {
      q.push(cur.left);
    }
    if (cur.right) {
      q.push(cur.right);
    }
  }
  return root;
}