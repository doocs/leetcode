/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var res int

func sumEvenGrandparent(root *TreeNode) int {
	res = 0
	dfs(root, root.Left)
	dfs(root, root.Right)
	return res
}

func dfs(g, p *TreeNode) {
	if p == nil {
		return
	}
	if g.Val%2 == 0 {
		if p.Left != nil {
			res += p.Left.Val
		}
		if p.Right != nil {
			res += p.Right.Val
		}
	}
	dfs(p, p.Left)
	dfs(p, p.Right)
}