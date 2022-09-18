/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func reverseOddLevels(root *TreeNode) *TreeNode {
	q := []*TreeNode{root}
	i := 0
	for len(q) > 0 {
		t := []*TreeNode{}
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			if i%2 == 1 {
				t = append(t, node)
			}
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if len(t) > 0 {
			j, k := 0, len(t)-1
			for ; j < k; j, k = j+1, k-1 {
				v := t[j].Val
				t[j].Val = t[k].Val
				t[k].Val = v
			}
		}
		i++
	}
	return root
}