/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maximumAverageSubtree(root *TreeNode) float64 {
	var ans float64
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{0, 0}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		s := l[0] + root.Val + r[0]
		n := l[1] + 1 + r[1]
		ans = math.Max(ans, float64(s)/float64(n))
		return []int{s, n}
	}
	dfs(root)
	return ans
}