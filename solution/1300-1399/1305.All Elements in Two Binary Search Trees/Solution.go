/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func getAllElements(root1 *TreeNode, root2 *TreeNode) []int {
	var dfs func(root *TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{}
		}
		left := dfs(root.Left)
		right := dfs(root.Right)
		left = append(left, root.Val)
		left = append(left, right...)
		return left
	}
	merge := func(t1, t2 []int) []int {
		var ans []int
		i, j := 0, 0
		for i < len(t1) && j < len(t2) {
			if t1[i] <= t2[j] {
				ans = append(ans, t1[i])
				i++
			} else {
				ans = append(ans, t2[j])
				j++
			}
		}
		for i < len(t1) {
			ans = append(ans, t1[i])
			i++
		}
		for j < len(t2) {
			ans = append(ans, t2[j])
			j++
		}
		return ans
	}
	t1, t2 := dfs(root1), dfs(root2)
	return merge(t1, t2)
}