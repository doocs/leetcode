/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func averageOfSubtree(root *TreeNode) int {
	ans := 0
	var dfs func(*TreeNode) (int, int)
	dfs = func(root *TreeNode) (int, int) {
		if root == nil {
			return 0, 0
		}
		ls, ln := dfs(root.Left)
		rs, rn := dfs(root.Right)
		s := ls + rs + root.Val
		n := ln + rn + 1
		if s/n == root.Val {
			ans++
		}
		return s, n
	}
	dfs(root)
	return ans
}