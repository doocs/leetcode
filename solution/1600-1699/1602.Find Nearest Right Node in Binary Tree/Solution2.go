/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findNearestRightNode(root *TreeNode, u *TreeNode) *TreeNode {
	d := 0
	var ans *TreeNode
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, i int) {
		if root == nil || ans != nil {
			return
		}
		if d == i {
			ans = root
			return
		}
		if root == u {
			d = i
			return
		}
		dfs(root.Left, i+1)
		dfs(root.Right, i+1)
	}
	dfs(root, 1)
	return ans
}