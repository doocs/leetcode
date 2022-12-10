/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func hasPathSum(root *TreeNode, targetSum int) bool {
	var dfs func(*TreeNode, int) bool
	dfs = func(root *TreeNode, s int) bool {
		if root == nil {
			return false
		}
		s += root.Val
		if root.Left == nil && root.Right == nil && s == targetSum {
			return true
		}
		return dfs(root.Left, s) || dfs(root.Right, s)
	}
	return dfs(root, 0)
}