/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func leafSimilar(root1 *TreeNode, root2 *TreeNode) bool {
	l1, l2 := []int{}, []int{}
	var dfs func(*TreeNode, *[]int)
	dfs = func(root *TreeNode, nums *[]int) {
		if root.Left == root.Right {
			*nums = append(*nums, root.Val)
			return
		}
		if root.Left != nil {
			dfs(root.Left, nums)
		}
		if root.Right != nil {
			dfs(root.Right, nums)
		}
	}
	dfs(root1, &l1)
	dfs(root2, &l2)
	return reflect.DeepEqual(l1, l2)
}
