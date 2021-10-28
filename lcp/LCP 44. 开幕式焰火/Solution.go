/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
var s map[int]bool

func numColor(root *TreeNode) int {
	s = make(map[int]bool)
	dfs(root)
	return len(s)
}

func dfs(root *TreeNode) {
	if root != nil {
		s[root.Val] = true
		dfs(root.Left)
		dfs(root.Right)
	}
}