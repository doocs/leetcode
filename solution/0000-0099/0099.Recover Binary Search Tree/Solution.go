/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func recoverTree(root *TreeNode) {
	var prev *TreeNode
	var first *TreeNode
	var second *TreeNode

	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if prev != nil {
			if first == nil && prev.Val > root.Val {
				first = prev
			}
			if first != nil && prev.Val > root.Val {
				second = root
			}
		}
		prev = root
		dfs(root.Right)
	}

	dfs(root)
	first.Val, second.Val = second.Val, first.Val
}