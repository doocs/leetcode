/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func largestBSTSubtree(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{math.MaxInt32, math.MinInt32, 0}
		}
		left := dfs(root.Left)
		right := dfs(root.Right)
		if left[1] < root.Val && root.Val < right[0] {
			ans = max(ans, left[2]+right[2]+1)
			return []int{min(root.Val, left[0]), max(root.Val, right[1]), left[2] + right[2] + 1}
		}
		return []int{math.MinInt32, math.MaxInt32, 0}
	}
	dfs(root)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}