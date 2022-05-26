/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func generateTrees(n int) []*TreeNode {
	var gen func(left, right int) []*TreeNode
	gen = func(left, right int) []*TreeNode {
		var ans []*TreeNode
		if left > right {
			ans = append(ans, nil)
		} else {
			for i := left; i <= right; i++ {
				leftTrees := gen(left, i-1)
				rightTrees := gen(i+1, right)
				for _, l := range leftTrees {
					for _, r := range rightTrees {
						node := &TreeNode{i, l, r}
						ans = append(ans, node)
					}
				}
			}
		}
		return ans
	}

	return gen(1, n)
}