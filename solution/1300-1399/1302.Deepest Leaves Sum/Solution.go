/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func deepestLeavesSum(root *TreeNode) (ans int) {
	q := []*TreeNode{root}
	for len(q) > 0 {
		ans = 0
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			ans += node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
	}
	return
}
