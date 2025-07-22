/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func largestValues(root *TreeNode) (ans []int) {
	if root == nil {
		return
	}
	q := []*TreeNode{root}
	for len(q) > 0 {
		x := q[0].Val
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			x = max(x, node.Val)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans = append(ans, x)
	}
	return
}
