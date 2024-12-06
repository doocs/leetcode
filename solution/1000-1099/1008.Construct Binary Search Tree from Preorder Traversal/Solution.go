/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func bstFromPreorder(preorder []int) *TreeNode {
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		root := &TreeNode{Val: preorder[i]}
		l, r := i+1, j+1
		for l < r {
			mid := (l + r) >> 1
			if preorder[mid] > preorder[i] {
				r = mid
			} else {
				l = mid + 1
			}
		}
		root.Left = dfs(i+1, l-1)
		root.Right = dfs(l, j)
		return root
	}
	return dfs(0, len(preorder)-1)
}
