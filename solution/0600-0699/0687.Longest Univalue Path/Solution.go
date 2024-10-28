/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func longestUnivaluePath(root *TreeNode) (ans int) {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if root.Left != nil && root.Left.Val == root.Val {
			l++
		} else {
			l = 0
		}
		if root.Right != nil && root.Right.Val == root.Val {
			r++
		} else {
			r = 0
		}
		ans = max(ans, l+r)
		return max(l, r)
	}
	dfs(root)
	return
}
