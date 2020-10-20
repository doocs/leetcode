/**
 * // Definition for a Node.
 * function Node(val, left, right) {
 *      this.val = val;
 *      this.left = left;
 *      this.right = right;
 *  };
 */
/**
 * @param {Node} root
 * @return {Node}
 */
var treeToDoublyList = function (root) {
  if (!root) return null;
  function dfs(node) {
    if (!node) return null;
    dfs(node.left);
    arr.push(node);
    dfs(node.right);
  }
  let arr = [];
  dfs(root);
  let len = arr.length;
  let res = arr[0];
  for (let i = 0; i < len; i++) {
    if (i + 1 < len) {
      arr[i].right = arr[i + 1];
    } else {
      arr[i].right = arr[0];
    }
    if (i - 1 >= 0) {
      arr[i].left = arr[i - 1];
    } else {
      arr[i].left = arr[len - 1];
    }
  }
  return res;
};
