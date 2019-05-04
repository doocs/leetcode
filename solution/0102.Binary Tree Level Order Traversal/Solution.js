/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
var levelOrder = function(root) {
  let result = []
  if (!root) return result

  let queue = []
  queue.push(root)
  while(queue.length) {
    let size = queue.length
    let levelItems = []
    while(size--) {
      let node = queue.shift()
      levelItems.push(node.val)
      if(node.left) {
        queue.push(node.left)
      }
      if(node.right) {
        queue.push(node.right)
      }
    }
    result.push(levelItems)
  }
  return result
};