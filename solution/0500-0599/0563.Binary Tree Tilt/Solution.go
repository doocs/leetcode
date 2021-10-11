/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
var ans int

func findTilt(root *TreeNode) int {
	ans = 0
	sum(root)
	return ans
}

func sum(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left, right := sum(root.Left), sum(root.Right)
	ans += abs(left - right)
	return root.Val + left + right
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}