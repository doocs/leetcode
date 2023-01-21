/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isBalanced(root *TreeNode) bool {
	var height func(*TreeNode) int
	height = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := height(root.Left), height(root.Right)
		if l == -1 || r == -1 || abs(l-r) > 1 {
			return -1
		}
		if l > r {
			return 1 + l
		}
		return 1 + r
	}
	return height(root) >= 0
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}