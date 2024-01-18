/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func insertIntoMaxTree(root *TreeNode, val int) *TreeNode {
	if root.Val < val {
		return &TreeNode{val, root, nil}
	}
	node := &TreeNode{Val: val}
	curr := root
	for curr.Right != nil && curr.Right.Val > val {
		curr = curr.Right
	}
	node.Left = curr.Right
	curr.Right = node
	return root
}