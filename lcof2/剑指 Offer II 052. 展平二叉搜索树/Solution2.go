/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func increasingBST(root *TreeNode) *TreeNode {
	dummy := &TreeNode{}
	cur := dummy
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		root.Left = nil
		cur.Right = root
		cur = root
		dfs(root.Right)
	}
	dfs(root)
	return dummy.Right
}