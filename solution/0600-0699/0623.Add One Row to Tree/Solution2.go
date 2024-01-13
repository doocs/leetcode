/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func addOneRow(root *TreeNode, val int, depth int) *TreeNode {
	if depth == 1 {
		return &TreeNode{val, root, nil}
	}
	q := []*TreeNode{root}
	i := 0
	for len(q) > 0 {
		i++
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
			if i == depth-1 {
				node.Left = &TreeNode{val, node.Left, nil}
				node.Right = &TreeNode{val, nil, node.Right}
			}
		}
	}
	return root
}