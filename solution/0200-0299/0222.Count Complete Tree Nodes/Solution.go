/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	depth := func(root *TreeNode) int {
		res := 0
		for root != nil {
			res++
			root = root.Left
		}
		return res
	}
	left, right := depth(root.Left), depth(root.Right)
	if left == right {
		return (1 << left) + countNodes(root.Right)
	}
	return (1 << right) + countNodes(root.Left)
}