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
var isValidBST = function(root) {
  if (root == null) return true
  let arr = []
  inOrderTraverse(root, arr)
  for (let i = 0; i < arr.length - 1; i++) {
    if (arr[i] >= arr[i + 1]) return false
  }
  return true
}

var inOrderTraverse = function(node, arr) {
  if (node !== null) {
    inOrderTraverse(node.left, arr)
    arr.push(node.val)
    inOrderTraverse(node.right, arr)
  }
}