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
	for i := 0; len(q) > 0; i++ {
		t := []*TreeNode{}
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			if i%2 == 1 {
				t = append(t, node)
			}
			if node.Left != nil {
				q = append(q, node.Left)
				q = append(q, node.Right)
			}
		}
		for l, r := 0, len(t)-1; l < r; l, r = l+1, r-1 {
			t[l].Val, t[r].Val = t[r].Val, t[l].Val
		}
	}
	return root
}