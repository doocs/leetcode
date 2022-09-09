/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findNearestRightNode(root *TreeNode, u *TreeNode) *TreeNode {
	q := []*TreeNode{root}
	for len(q) > 0 {
		for i := len(q); i > 0; i-- {
			root = q[0]
			q = q[1:]
			if root == u {
				if i > 1 {
					return q[0]
				}
				return nil
			}
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
	}
	return nil
}