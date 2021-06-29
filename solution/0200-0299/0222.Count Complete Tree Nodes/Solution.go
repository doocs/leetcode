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
	leftDepth := depth(root.Left)
	rightDepth := depth(root.Right)
	if leftDepth > rightDepth {
		return (1 << rightDepth) + countNodes(root.Left)
	}
	return (1 << leftDepth) + countNodes(root.Right)
}

func depth(root *TreeNode) int {
	res := 0
	for root != nil {
		res++
		root = root.Left
	}
	return res
}