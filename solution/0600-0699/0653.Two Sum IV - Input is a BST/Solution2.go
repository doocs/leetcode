/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findTarget(root *TreeNode, k int) bool {
	q := []*TreeNode{root}
	vis := map[int]bool{}
	for len(q) > 0 {
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			if vis[k-node.Val] {
				return true
			}
			vis[node.Val] = true
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
	}
	return false
}