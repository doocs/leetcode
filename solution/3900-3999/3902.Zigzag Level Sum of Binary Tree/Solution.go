/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func zigzagLevelSum(root *TreeNode) []int64 {
	ans := []int64{}
	q := []*TreeNode{root}
	left := true
	for len(q) > 0 {
		nq := []*TreeNode{}
		for _, node := range q {
			if node.Left != nil {
				nq = append(nq, node.Left)
			}
			if node.Right != nil {
				nq = append(nq, node.Right)
			}
		}
		m := len(q)
		var s int64 = 0
		for i := 0; i < m; i++ {
			var node *TreeNode
			if left {
				node = q[i]
			} else {
				node = q[m-i-1]
			}
			var child *TreeNode
			if left {
				child = node.Left
			} else {
				child = node.Right
			}
			if child == nil {
				break
			}
			s += int64(node.Val)
		}
		ans = append(ans, s)
		left = !left
		q = nq
	}
	return ans
}
