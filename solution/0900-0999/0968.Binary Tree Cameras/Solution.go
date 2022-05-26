/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minCameraCover(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 2
		}
		left, right := dfs(root.Left), dfs(root.Right)
		if left == 0 || right == 0 {
			ans++
			return 1
		}
		if left == 1 || right == 1 {
			return 2
		}
		return 0
	}
	if dfs(root) == 0 {
		return ans + 1
	}
	return ans
}