/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pathSum(root *TreeNode, target int) (ans [][]int) {
	t := []int{}
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, s int) {
		if root == nil {
			return
		}
		t = append(t, root.Val)
		s -= root.Val
		if root.Left == nil && root.Right == nil && s == 0 {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
		}
		dfs(root.Left, s)
		dfs(root.Right, s)
		t = t[:len(t)-1]
	}
	dfs(root, target)
	return
}