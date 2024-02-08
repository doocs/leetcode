/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isCousins(root *TreeNode, x int, y int) bool {
	type pair struct{ node, parent *TreeNode }
	var d1, d2 int
	var p1, p2 *TreeNode
	q := []pair{{root, nil}}
	for depth := 0; len(q) > 0; depth++ {
		for n := len(q); n > 0; n-- {
			node, parent := q[0].node, q[0].parent
			q = q[1:]
			if node.Val == x {
				d1, p1 = depth, parent
			} else if node.Val == y {
				d2, p2 = depth, parent
			}
			if node.Left != nil {
				q = append(q, pair{node.Left, node})
			}
			if node.Right != nil {
				q = append(q, pair{node.Right, node})
			}
		}
	}
	return d1 == d2 && p1 != p2
}