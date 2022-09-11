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
	q := []*TreeNode{root}
	for len(q) > 0 {
		var prev int = 1e6
		if even {
			prev = 0
		}
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			if even && (root.Val%2 == 0 || prev >= root.Val) {
				return false
			}
			if !even && (root.Val%2 == 1 || prev <= root.Val) {
				return false
			}
			prev = root.Val
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
		even = !even
	}
	return true
}