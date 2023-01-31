/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func buildTree(preorder []int, inorder []int) *TreeNode {
	d := map[int]int{}
	for i, v := range inorder {
		d[v] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n < 1 {
			return nil
		}
		k := d[preorder[i]]
		l := k - j
		root := &TreeNode{Val: preorder[i]}
		root.Left = dfs(i+1, j, l)
		root.Right = dfs(i+1+l, k+1, n-l-1)
		return root
	}
	return dfs(0, 0, len(inorder))
}