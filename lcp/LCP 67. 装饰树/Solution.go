/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func expandBinaryTree(root *TreeNode) *TreeNode {
	var dfs func(*TreeNode) *TreeNode
	dfs = func(root *TreeNode) *TreeNode {
		if root == nil {
			return root
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if l != nil {
			root.Left = &TreeNode{-1, l, nil}
		}
		if r != nil {
			root.Right = &TreeNode{-1, nil, r}
		}
		return root
	}
	return dfs(root)
}