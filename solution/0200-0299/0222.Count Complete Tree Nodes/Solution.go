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
	left, right := depth(root.Left), depth(root.Right)
	if left == right {
		return (1 << left) + countNodes(root.Right)
	}
	return (1 << right) + countNodes(root.Left)
}

func depth(root *TreeNode) (d int) {
	for ; root != nil; root = root.Left {
		d++
	}
	return
}