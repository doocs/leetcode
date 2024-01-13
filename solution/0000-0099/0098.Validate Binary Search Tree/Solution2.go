/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isValidBST(root *TreeNode) bool {
	return dfs(root, math.MinInt64, math.MaxInt64)
}

func dfs(root *TreeNode, l, r int64) bool {
	if root == nil {
		return true
	}
	v := int64(root.Val)
	if v <= l || v >= r {
		return false
	}
	return dfs(root.Left, l, v) && dfs(root.Right, v, r)
}