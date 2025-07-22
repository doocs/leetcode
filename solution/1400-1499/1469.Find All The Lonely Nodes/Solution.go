/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 func getLonelyNodes(root *TreeNode) (ans []int) {
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil || (root.Left == root.Right) {
			return
		}
		if root.Left == nil {
			ans = append(ans, root.Right.Val)
		}
		if root.Right == nil {
			ans = append(ans, root.Left.Val)
		}
		dfs(root.Left)
		dfs(root.Right)
	}
	dfs(root)
	return
}
