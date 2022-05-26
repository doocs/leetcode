/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func increasingBST(root *TreeNode) *TreeNode {
	var head, tail *TreeNode
	stack := make([]*TreeNode, 0)
	cur := root
	for len(stack) > 0 || cur != nil {
		for cur != nil {
			stack = append(stack, cur)
			cur = cur.Left
		}
		cur = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if head == nil {
			head = cur
		} else {
			tail.Right = cur
		}
		tail = cur
		cur.Left = nil
		cur = cur.Right
	}
	return head
}
