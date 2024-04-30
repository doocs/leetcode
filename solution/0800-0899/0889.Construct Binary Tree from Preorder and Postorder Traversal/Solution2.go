/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	pos := map[int]int{}
	for i, x := range postorder {
		pos[x] = i
	}
	var dfs func(int, int, int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		root := &TreeNode{Val: preorder[i]}
		if n == 1 {
			return root
		}
		k := pos[preorder[i+1]]
		m := k - j + 1
		root.Left = dfs(i+1, j, m)
		root.Right = dfs(i+m+1, k+1, n-m-1)
		return root
	}
	return dfs(0, 0, len(preorder))
}