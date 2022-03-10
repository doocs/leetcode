/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func postorderTraversal(root *TreeNode) []int {
	var ans []int
	for root != nil {
		if root.Right == nil {
			ans = append([]int{root.Val}, ans...)
			root = root.Left
		} else {
			next := root.Right
			for next.Left != nil && next.Left != root {
				next = next.Left
			}
			if next.Left == nil {
				ans = append([]int{root.Val}, ans...)
				next.Left = root
				root = root.Right
			} else {
				next.Left = nil
				root = root.Left
			}
		}
	}
	return ans
}