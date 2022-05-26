/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func zigzagLevelOrder(root *TreeNode) [][]int {
	if root == nil {
		return nil
	}
	var ans [][]int
	var q = []*TreeNode{root}
	left := false
	for len(q) > 0 {
		var t []int
		n := len(q)
		for i := 0; i < n; i++ {
			node := q[0]
			q = q[1:]
			t = append(t, node.Val)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if left {
			i, j := 0, n-1
			for i < j {
				t[i], t[j] = t[j], t[i]
				i++
				j--
			}
		}
		ans = append(ans, t)
		left = !left
	}
	return ans
}