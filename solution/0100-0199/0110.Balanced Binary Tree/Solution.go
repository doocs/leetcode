/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isBalanced(root *TreeNode) bool {
	return height(root) >= 0
}

func height(root *TreeNode) int {
	if root == nil {
		return 0
	}
	l, r := height(root.Left), height(root.Right)
	if l == -1 || r == -1 || abs(l-r) > 1 {
		return -1
	}
	return 1 + max(l, r)
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}