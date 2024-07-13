/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minimumLevel(root *TreeNode) (ans int) {
	q := []*TreeNode{root}
	s := math.MaxInt64
	for level := 1; len(q) > 0; level++ {
		t := 0
		for m := len(q); m > 0; m-- {
			node := q[0]
			q = q[1:]
			t += node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if s > t {
			s = t
			ans = level
		}
	}
	return
}