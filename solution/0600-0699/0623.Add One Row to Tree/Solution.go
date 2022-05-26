/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func addOneRow(root *TreeNode, val int, depth int) *TreeNode {
	if depth == 1 {
		return &TreeNode{Val: val, Left: root}
	}
	var dfs func(root *TreeNode, d int)
	dfs = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		if d == depth-1 {
			l, r := &TreeNode{Val: val, Left: root.Left}, &TreeNode{Val: val, Right: root.Right}
			root.Left, root.Right = l, r
			return
		}
		dfs(root.Left, d+1)
		dfs(root.Right, d+1)
	}
	dfs(root, 1)
	return root
}