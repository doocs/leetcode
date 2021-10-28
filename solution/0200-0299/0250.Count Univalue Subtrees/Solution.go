/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
var cnt int

func countUnivalSubtrees(root *TreeNode) int {
	if root == nil {
		return 0
	}
	cnt = 0
	dfs(root)
	return cnt
}

func dfs(root *TreeNode) bool {
	if root.Left == nil && root.Right == nil {
		cnt++
		return true
	}
	res := true
	if root.Left != nil {
		res = dfs(root.Left) && res && root.Val == root.Left.Val
	}
	if root.Right != nil {
		res = dfs(root.Right) && res && root.Val == root.Right.Val
	}
	if res {
		cnt++
	}
	return res
}