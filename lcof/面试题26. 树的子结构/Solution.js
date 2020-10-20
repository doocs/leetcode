/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} A
 * @param {TreeNode} B
 * @return {boolean}
 */
var isSubStructure = function (A, B) {
  if (!B || !A) return false;
  let res;
  function dfs(A, B, bool) {
    if (!A || !B) {
      if (B) {
        return false;
      } else {
        return true;
      }
    }
    if (A.val === B.val) {
      let left = dfs(A.left, B.left, true);
      let right = dfs(A.right, B.right, true);
      if (left && right) return true;
      else return false;
    } else {
      if (bool) return false;
      else {
        let left = dfs(A.left, B, false);
        let right = dfs(A.right, B, false);
        return left || right;
      }
    }
  }
  return dfs(A, B, false) || false;
};
