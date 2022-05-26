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
		if i > j || i >= len(preorder) {
			return nil
		}
		root := &TreeNode{Val: preorder[i]}
		left, right := i+1, len(preorder)
		for left < right {
			mid := (left + right) >> 1
			if preorder[mid] > preorder[i] {
				right = mid
			} else {
				left = mid + 1
			}
		}
		root.Left = dfs(i+1, left-1)
		root.Right = dfs(left, j)
		return root
	}
	return dfs(0, len(preorder)-1)
}