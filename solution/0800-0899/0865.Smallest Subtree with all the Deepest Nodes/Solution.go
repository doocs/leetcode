/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func subtreeWithAllDeepest(root *TreeNode) *TreeNode {
	type pair struct {
		node  *TreeNode
		depth int
	}
	var dfs func(*TreeNode) pair
	dfs = func(root *TreeNode) pair {
		if root == nil {
			return pair{nil, 0}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		ld, rd := l.depth, r.depth
		if ld > rd {
			return pair{l.node, ld + 1}
		}
		if ld < rd {
			return pair{r.node, rd + 1}
		}
		return pair{root, ld + 1}
	}
	return dfs(root).node
}
