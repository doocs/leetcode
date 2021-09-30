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
var isValidBST = function (root) {
  if (root == null) return true;
  let arr = [];
  inOrderTraverse(root, arr);
  for (let i = 0; i < arr.length - 1; i++) {
    if (arr[i] >= arr[i + 1]) return false;
  }
  return true;
};

var inOrderTraverse = function (node, arr) {
  if (node !== null) {
    inOrderTraverse(node.left, arr);
    arr.push(node.val);
    inOrderTraverse(node.right, arr);
  }
};

//Solution 2
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isValidBST = function (root) {
    let isValidBSTRec = function (root, min, max) {
        if (!root) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBSTRec(root.left, min, root.val) && isValidBSTRec(root.right, root.val, max);
    }
    return isValidBSTRec(root, -Infinity, Infinity);
};