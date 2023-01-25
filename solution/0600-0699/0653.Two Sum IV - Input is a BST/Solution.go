/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findTarget(root *TreeNode, k int) bool {
	vis := map[int]bool{}
	var dfs func(*TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return false
		}
		if vis[k-root.Val] {
			return true
		}
		vis[root.Val] = true
		return dfs(root.Left) || dfs(root.Right)
	}
	return dfs(root)
}