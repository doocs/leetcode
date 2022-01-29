/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxLevelSum(root *TreeNode) int {
	ans := [2]int{math.MinInt32, 0}
	q := []*TreeNode{root}
	l := 0
	for len(q) > 0 {
		l++
		s := 0
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			s += node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if s > ans[0] {
			ans = [2]int{s, l}
		}
	}
	return ans[1]
}