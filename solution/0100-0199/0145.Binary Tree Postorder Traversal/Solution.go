/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func postorderTraversal(root *TreeNode) []int {
	var res []int
	for root != nil {
		if root.Right == nil {
			res = append([]int{root.Val}, res...)
			root = root.Left
		} else {
			next := root.Right
			for next.Left != nil && next.Left != root {
				next = next.Left
			}
			if next.Left == nil {
				res = append([]int{root.Val}, res...)
				next.Left = root
				root = root.Right
			} else {
				next.Left = nil
				root = root.Left
			}
		}
	}
	return res
}