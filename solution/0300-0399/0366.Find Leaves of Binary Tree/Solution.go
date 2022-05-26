/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findLeaves(root *TreeNode) [][]int {
	prev := &TreeNode{
		Val:   0,
		Left:  root,
		Right: nil,
	}
	var res [][]int
	for prev.Left != nil {
		var t []int
		dfs(prev.Left, prev, &t)
		res = append(res, t)
	}
	return res
}

func dfs(root, prev *TreeNode, t *[]int) {
	if root == nil {
		return
	}
	if root.Left == nil && root.Right == nil {
		*t = append(*t, root.Val)
		if prev.Left == root {
			prev.Left = nil
		} else {
			prev.Right = nil
		}
	}
	dfs(root.Left, root, t)
	dfs(root.Right, root, t)
}