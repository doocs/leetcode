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
	dfs1 = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		if len(s) <= d {
			s = append(s, 0)
		}
		s[d] += root.Val
		dfs1(root.Left, d+1)
		dfs1(root.Right, d+1)
	}
	var dfs2 func(*TreeNode, int)
	dfs2 = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		l, r := 0, 0
		if root.Left != nil {
			l = root.Left.Val
		}
		if root.Right != nil {
			r = root.Right.Val
		}
		if root.Left != nil {
			root.Left.Val = s[d] - l - r
		}
		if root.Right != nil {
			root.Right.Val = s[d] - l - r
		}
		dfs2(root.Left, d+1)
		dfs2(root.Right, d+1)
	}
	dfs1(root, 0)
	root.Val = 0
	dfs2(root, 1)
	return root
}