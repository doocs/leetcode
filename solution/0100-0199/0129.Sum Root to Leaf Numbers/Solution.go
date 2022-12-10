/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumNumbers(root *TreeNode) int {
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, s int) int {
		if root == nil {
			return 0
		}
		s = s*10 + root.Val
		if root.Left == nil && root.Right == nil {
			return s
		}
		return dfs(root.Left, s) + dfs(root.Right, s)
	}
	return dfs(root, 0)
}