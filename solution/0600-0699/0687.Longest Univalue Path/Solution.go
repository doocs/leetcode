/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
var res int

func longestUnivaluePath(root *TreeNode) int {
	res = 0
	dfs(root)
	return res
}

func dfs(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left, right := dfs(root.Left), dfs(root.Right)
	if root.Left != nil && root.Left.Val == root.Val {
		left++
	} else {
		left = 0
	}
	if root.Right != nil && root.Right.Val == root.Val {
		right++
	} else {
		right = 0
	}
	res = max(res, left+right)
	return max(left, right)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}