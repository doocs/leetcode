/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isCousins(root *TreeNode, x int, y int) bool {
	p := make([]int, 110)
	d := make([]int, 110)
	var q []*TreeNode
	q = append(q, root)
	i := 0
	for len(q) > 0 {
		n := len(q)
		for n > 0 {
			node := q[0]
			q = q[1:]
			n--
			d[node.Val] = i
			if node.Left != nil {
				q = append(q, node.Left)
				p[node.Left.Val] = node.Val
			}
			if node.Right != nil {
				q = append(q, node.Right)
				p[node.Right.Val] = node.Val
			}
		}
		i++
	}
	return p[x] != p[y] && d[x] == d[y]
}