/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func removeLeafNodes(root *TreeNode, target int) *TreeNode {
	p := &TreeNode{0, root, nil}
	var dfs func(root, prev *TreeNode)
	dfs = func(root, prev *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left, root)
		dfs(root.Right, root)
		if root.Left == nil && root.Right == nil && root.Val == target {
			if prev.Left == root {
				prev.Left = nil
			} else {
				prev.Right = nil
			}
		}
	}
	dfs(root, p)
	return p.Left
}