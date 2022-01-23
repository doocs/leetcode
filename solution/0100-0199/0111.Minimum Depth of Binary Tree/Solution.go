/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minDepth(root *TreeNode) int {
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if root.Left == nil {
			return 1 + dfs(root.Right)
		}
		if root.Right == nil {
			return 1 + dfs(root.Left)
		}
		return 1 + min(dfs(root.Left), dfs(root.Right))
	}
	return dfs(root)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}