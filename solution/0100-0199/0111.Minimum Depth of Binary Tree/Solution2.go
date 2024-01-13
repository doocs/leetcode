/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minDepth(root *TreeNode) (ans int) {
	if root == nil {
		return 0
	}
	q := []*TreeNode{root}
	for {
		ans++
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			if node.Left == nil && node.Right == nil {
				return
			}
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
	}
}