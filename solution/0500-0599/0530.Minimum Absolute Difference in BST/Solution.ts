/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */
function getMinimumDifference(root: TreeNode | null): number {
    if (!root) return 0
  
    let prev = Number.MIN_SAFE_INTEGER
    let min = Number.MAX_SAFE_INTEGER
  
    const dfs = (node: TreeNode | null) => {
      if (!node) return
  
      dfs(node.left)
      min = Math.min(min, node.val - prev)
      prev = node.val
      dfs(node.right)
    }
  
    dfs(root)
  
    return min
  }