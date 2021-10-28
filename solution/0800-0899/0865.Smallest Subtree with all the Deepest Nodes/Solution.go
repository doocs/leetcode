/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type Result struct {
	Node  *TreeNode
	Depth int
}

func subtreeWithAllDeepest(root *TreeNode) *TreeNode {
	return dfs(root).Node
}

func dfs(root *TreeNode) Result {
	if root == nil {
		return Result{
			nil, 0,
		}
	}
	left, right := dfs(root.Left), dfs(root.Right)
	d1, d2 := left.Depth, right.Depth
	if d1 > d2 {
		return Result{left.Node, d1 + 1}
	}
	if d1 < d2 {
		return Result{right.Node, d2 + 1}
	}
	return Result{root, d1 + 1}
}