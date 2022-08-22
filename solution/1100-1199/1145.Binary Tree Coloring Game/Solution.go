/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func btreeGameWinningMove(root *TreeNode, n int, x int) bool {
	var dfs func(*TreeNode) *TreeNode
	dfs = func(root *TreeNode) *TreeNode {
		if root == nil || root.Val == x {
			return root
		}
		l := dfs(root.Left)
		if l != nil {
			return l
		}
		return dfs(root.Right)
	}
	var count func(*TreeNode) int
	count = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		return 1 + count(root.Left) + count(root.Right)
	}
	node := dfs(root)
	l, r := count(node.Left), count(node.Right)
	m := max(max(l, r), n-l-r-1)
	return m > n-m
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}