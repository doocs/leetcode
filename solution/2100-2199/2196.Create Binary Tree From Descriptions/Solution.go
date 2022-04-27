/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func createBinaryTree(descriptions [][]int) *TreeNode {
	m := make(map[int]*TreeNode)
	vis := make(map[int]bool)
	for _, d := range descriptions {
		p, c, left := d[0], d[1], d[2]
		if m[p] == nil {
			m[p] = &TreeNode{Val: p}
		}
		if m[c] == nil {
			m[c] = &TreeNode{Val: c}
		}
		if left == 1 {
			m[p].Left = m[c]
		} else {
			m[p].Right = m[c]
		}
		vis[c] = true
	}

	for v, node := range m {
		if !vis[v] {
			return node
		}
	}
	return nil
}