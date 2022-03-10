/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func buildTree(inorder []int, postorder []int) *TreeNode {
	indexes := make(map[int]int)
	for i, v := range inorder {
		indexes[v] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		v := postorder[j+n-1]
		k := indexes[v]
		root := &TreeNode{Val: v}
		root.Left = dfs(i, j, k-i)
		root.Right = dfs(k+1, j+k-i, n-k+i-1)
		return root
	}
	return dfs(0, 0, len(inorder))
}