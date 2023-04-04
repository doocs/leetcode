/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countUnivalSubtrees(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return true
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if !l || !r {
			return false
		}
		if root.Left != nil && root.Left.Val != root.Val {
			return false
		}
		if root.Right != nil && root.Right.Val != root.Val {
			return false
		}
		ans++
		return true
	}
	dfs(root)
	return
}