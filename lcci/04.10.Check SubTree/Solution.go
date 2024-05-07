/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func checkSubTree(t1 *TreeNode, t2 *TreeNode) bool {
	var dfs func(t1, t2 *TreeNode) bool
	dfs = func(t1, t2 *TreeNode) bool {
		if t2 == nil {
			return t1 == nil
		}
		if t1 == nil || t1.Val != t2.Val {
			return false
		}
		return dfs(t1.Left, t2.Left) && dfs(t1.Right, t2.Right)
	}
	if t2 == nil {
		return true
	}
	if t1 == nil {
		return false
	}
	if dfs(t1, t2) {
		return true
	}
	return checkSubTree(t1.Left, t2) || checkSubTree(t1.Right, t2)
}