/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxLevelSum(root *TreeNode) int {
	q := []*TreeNode{root}
	mx := -0x3f3f3f3f
	i := 0
	ans := 0
	for len(q) > 0 {
		i++
		s := 0
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			s += root.Val
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
		if mx < s {
			mx = s
			ans = i
		}
	}
	return ans
}