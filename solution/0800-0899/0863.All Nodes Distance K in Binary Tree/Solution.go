/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func distanceK(root *TreeNode, target *TreeNode, k int) []int {
	p := make(map[*TreeNode]*TreeNode)
	vis := make(map[int]bool)
	var ans []int
	var parents func(root, prev *TreeNode)
	parents = func(root, prev *TreeNode) {
		if root == nil {
			return
		}
		p[root] = prev
		parents(root.Left, root)
		parents(root.Right, root)
	}
	parents(root, nil)
	var dfs func(root *TreeNode, k int)
	dfs = func(root *TreeNode, k int) {
		if root == nil || vis[root.Val] {
			return
		}
		vis[root.Val] = true
		if k == 0 {
			ans = append(ans, root.Val)
			return
		}
		dfs(root.Left, k-1)
		dfs(root.Right, k-1)
		dfs(p[root], k-1)
	}
	dfs(target, k)
	return ans
}