/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func flatten(root *TreeNode) {
	for root != nil {
		left, right := root.Left, root.Right
		root.Left = nil
		if left != nil {
			root.Right = left
			for left.Right != nil {
				left = left.Right
			}
			left.Right = right
		}
		root = root.Right
	}
}
