/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pathSum(root *TreeNode, targetSum int) [][]int {
	ans := [][]int{}
	t := []int{}
	var dfs func(root *TreeNode, s int)
	dfs = func(root *TreeNode, s int) {
		if root == nil {
			return
		}
		t = append(t, root.Val)
		s += root.Val
		if root.Left == nil && root.Right == nil && s == targetSum {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
		}
		dfs(root.Left, s)
		dfs(root.Right, s)
		t = t[:len(t)-1]
	}
	dfs(root, 0)
	return ans
}