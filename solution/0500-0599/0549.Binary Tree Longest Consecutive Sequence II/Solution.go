/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func longestConsecutive(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{0, 0}
		}
		incr, decr := 1, 1
		left := dfs(root.Left)
		right := dfs(root.Right)
		if root.Left != nil {
			if root.Left.Val+1 == root.Val {
				incr = left[0] + 1
			}
			if root.Left.Val-1 == root.Val {
				decr = left[1] + 1
			}
		}
		if root.Right != nil {
			if root.Right.Val+1 == root.Val {
				incr = max(incr, right[0]+1)
			}
			if root.Right.Val-1 == root.Val {
				decr = max(decr, right[1]+1)
			}
		}
		ans = max(ans, incr+decr-1)
		return []int{incr, decr}
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