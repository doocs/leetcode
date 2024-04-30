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
		t := []*TreeNode{}
		s := 0
		for _, node := range q {
			if node.Left != nil {
				t = append(t, node.Left)
				s += node.Left.Val
			}
			if node.Right != nil {
				t = append(t, node.Right)
				s += node.Right.Val
			}
		}
		for _, node := range q {
			sub := 0
			if node.Left != nil {
				sub += node.Left.Val
			}
			if node.Right != nil {
				sub += node.Right.Val
			}
			if node.Left != nil {
				node.Left.Val = s - sub
			}
			if node.Right != nil {
				node.Right.Val = s - sub
			}
		}
		q = t
	}
	return root
}