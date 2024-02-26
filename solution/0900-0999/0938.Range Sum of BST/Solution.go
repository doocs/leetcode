/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rangeSumBST(root *TreeNode, low int, high int) int {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) (ans int) {
		if root == nil {
			return 0
		}
		x := root.Val
		if low <= x && x <= high {
			ans += x
		}
		if x > low {
			ans += dfs(root.Left)
		}
		if x < high {
			ans += dfs(root.Right)
		}
		return
	}
	return dfs(root)
}