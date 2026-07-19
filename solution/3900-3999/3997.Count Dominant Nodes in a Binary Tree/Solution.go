/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countDominantNodes(root *TreeNode) int {
	ans := 0
	var dfs func(*TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return math.MinInt32
		}
		l := dfs(node.Left)
		r := dfs(node.Right)
		mx := max(l, r, node.Val)
		if mx == node.Val {
			ans++
		}
		return mx
	}
	dfs(root)
	return ans
}
