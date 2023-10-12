/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minimalExecTime(root *TreeNode) float64 {
	var dfs func(*TreeNode) (float64, float64)
	dfs = func(root *TreeNode) (float64, float64) {
		if root == nil {
			return 0, 0
		}
		s1, t1 := dfs(root.Left)
		s2, t2 := dfs(root.Right)
		s := s1 + s2 + float64(root.Val)
		t := math.Max(math.Max(t1, t2), (s1+s2)/2) + float64(root.Val)
		return s, t
	}
	_, t := dfs(root)
	return t
}