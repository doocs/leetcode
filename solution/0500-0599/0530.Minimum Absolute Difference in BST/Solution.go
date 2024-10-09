/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func getMinimumDifference(root *TreeNode) int {
	const inf int = 1 << 30
	ans, pre := inf, -inf
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		ans = min(ans, root.Val-pre)
		pre = root.Val
		dfs(root.Right)
	}
	dfs(root)
	return ans
}