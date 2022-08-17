/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func deepestLeavesSum(root *TreeNode) int {
	q := []*TreeNode{root}
	ans := 0
	for len(q) > 0 {
		ans = 0
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			ans += root.Val
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
	}
	return ans
}