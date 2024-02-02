/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func replaceValueInTree(root *TreeNode) *TreeNode {
	s := []int{}
	var dfs1 func(*TreeNode, int)
	dfs1 = func(root *TreeNode, depth int) {
		if root == nil {
			return
		}
		if len(s) <= depth {
			s = append(s, 0)
		}
		s[depth] += root.Val
		dfs1(root.Left, depth+1)
		dfs1(root.Right, depth+1)
	}
	var dfs2 func(*TreeNode, int)
	dfs2 = func(root *TreeNode, depth int) {
		l, r := 0, 0
		if root.Left != nil {
			l = root.Left.Val
		}
		if root.Right != nil {
			r = root.Right.Val
		}
		sub := l + r
		depth++
		if root.Left != nil {
			root.Left.Val = s[depth] - sub
			dfs2(root.Left, depth)
		}
		if root.Right != nil {
			root.Right.Val = s[depth] - sub
			dfs2(root.Right, depth)
		}
	}
	dfs1(root, 0)
	root.Val = 0
	dfs2(root, 0)
	return root
}