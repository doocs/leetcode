/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func evaluateTree(root *TreeNode) bool {
	var dfs func(*TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root.Left == nil && root.Right == nil {
			return root.Val == 1
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if root.Val == 2 {
			return l || r
		}
		return l && r
	}
	return dfs(root)
}