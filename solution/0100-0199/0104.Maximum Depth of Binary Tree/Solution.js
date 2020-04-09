/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
var maxDepth = function(root) {
  if (!root) return 0
  let depth = 1
  return search(root,depth)
};

function search(root, depth) {
  if (!root.left && !root.right) {
    return depth
  } else if (root.left && !root.right) {
    return search(root.left, depth + 1)
  } else if (root.right && !root.left) {
    return search(root.right, depth + 1)
  } else if (root.left && root.right) {
    return Math.max(search(root.left, depth+1), search(root.right,depth+1))
  }
}

var maxDepth2 = function(root) {
  if (!root) return 0
  return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1
}