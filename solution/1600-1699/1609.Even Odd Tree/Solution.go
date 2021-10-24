/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isEvenOddTree(root *TreeNode) bool {
	even := true
	var q []*TreeNode
	q = append(q, root)
	for len(q) > 0 {
		prev := 0
		if !even {
			prev = 1000000
		}
		n := len(q)
		for i := 0; i < n; i++ {
			node := q[0]
			q = q[1:]
			if even && (prev >= node.Val || node.Val%2 == 0) {
				return false
			}
			if !even && (prev <= node.Val || node.Val%2 == 1) {
				return false
			}
			prev = node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		even = !even
	}
	return true
}