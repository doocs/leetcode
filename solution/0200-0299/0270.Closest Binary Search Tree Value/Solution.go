/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func closestValue(root *TreeNode, target float64) int {
	ans := root.Val
	mi := math.MaxFloat64
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		t := math.Abs(float64(root.Val) - target)
		if t < mi {
			mi = t
			ans = root.Val
		}
		dfs(root.Right)
	}
	dfs(root)
	return ans
}