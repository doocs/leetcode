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
	diff := math.MaxFloat64
	var dfs func(*TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		nxt := math.Abs(float64(node.Val) - target)
		if nxt < diff || (nxt == diff && node.Val < ans) {
			diff = nxt
			ans = node.Val
		}
		if target < float64(node.Val) {
			dfs(node.Left)
		} else {
			dfs(node.Right)
		}
	}
	dfs(root)
	return ans
}