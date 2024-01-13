/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func replaceValueInTree(root *TreeNode) *TreeNode {
	root.Val = 0
	q := []*TreeNode{root}
	for len(q) > 0 {
		p := q
		q = []*TreeNode{}
		s := 0
		for _, node := range p {
			if node.Left != nil {
				q = append(q, node.Left)
				s += node.Left.Val
			}
			if node.Right != nil {
				q = append(q, node.Right)
				s += node.Right.Val
			}
		}
		for _, node := range p {
			t := 0
			if node.Left != nil {
				t += node.Left.Val
			}
			if node.Right != nil {
				t += node.Right.Val
			}
			if node.Left != nil {
				node.Left.Val = s - t
			}
			if node.Right != nil {
				node.Right.Val = s - t
			}
		}
	}
	return root
}