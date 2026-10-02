/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rob(root *TreeNode) int {
	if root == nil {
		return 0
	}
	order := []*TreeNode{}
	stack := []*TreeNode{root}
	for len(stack) > 0 {
		node := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		order = append(order, node)
		if node.Left != nil {
			stack = append(stack, node.Left)
		}
		if node.Right != nil {
			stack = append(stack, node.Right)
		}
	}
	type pair struct{ rob, skip int }
	dp := make(map[*TreeNode]pair, len(order))
	for i := len(order) - 1; i >= 0; i-- {
		node := order[i]
		left, right := dp[node.Left], dp[node.Right]
		dp[node] = pair{node.Val + left.skip + right.skip, max(left.rob, left.skip) + max(right.rob, right.skip)}
	}
	result := dp[root]
	a, b := result.rob, result.skip
	return max(a, b)
}
