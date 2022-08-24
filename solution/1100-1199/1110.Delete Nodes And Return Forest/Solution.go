/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func delNodes(root *TreeNode, to_delete []int) []*TreeNode {
	s := map[int]bool{}
	for _, v := range to_delete {
		s[v] = true
	}
	ans := []*TreeNode{}
	if !s[root.Val] {
		ans = append(ans, root)
	}
	var fa *TreeNode
	var dfs func(fa, root *TreeNode)
	dfs = func(fa, root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root, root.Left)
		dfs(root, root.Right)
		if s[root.Val] {
			if fa != nil && fa.Left == root {
				fa.Left = nil
			}
			if fa != nil && fa.Right == root {
				fa.Right = nil
			}
			if root.Left != nil {
				ans = append(ans, root.Left)
			}
			if root.Right != nil {
				ans = append(ans, root.Right)
			}
		}
	}
	dfs(fa, root)
	return ans
}