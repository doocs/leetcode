/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isCousins(root *TreeNode, x int, y int) bool {
	type pair struct{ node, fa *TreeNode }
	q := []pair{pair{root, nil}}
	var p1, p2 *TreeNode
	var d, d1, d2 int
	for len(q) > 0 {
		for n := len(q); n > 0; n-- {
			p := q[0]
			q = q[1:]
			node, fa := p.node, p.fa
			if node.Val == x {
				p1, d1 = fa, d
			}
			if node.Val == y {
				p2, d2 = fa, d
			}
			if node.Left != nil {
				q = append(q, pair{node.Left, node})
			}
			if node.Right != nil {
				q = append(q, pair{node.Right, node})
			}
		}
		d++
	}
	return p1 != p2 && d1 == d2
}