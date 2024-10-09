/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func boundaryOfBinaryTree(root *TreeNode) []int {
	ans := []int{root.Val}
	if root.Left == root.Right {
		return ans
	}

	left, leaves, right := []int{}, []int{}, []int{}

	var dfs func(nums *[]int, root *TreeNode, i int)
	dfs = func(nums *[]int, root *TreeNode, i int) {
		if root == nil {
			return
		}
		if i == 0 {
			if root.Left != root.Right {
				*nums = append(*nums, root.Val)
				if root.Left != nil {
					dfs(nums, root.Left, i)
				} else {
					dfs(nums, root.Right, i)
				}
			}
		} else if i == 1 {
			if root.Left == root.Right {
				*nums = append(*nums, root.Val)
			} else {
				dfs(nums, root.Left, i)
				dfs(nums, root.Right, i)
			}
		} else {
			if root.Left != root.Right {
				*nums = append(*nums, root.Val)
				if root.Right != nil {
					dfs(nums, root.Right, i)
				} else {
					dfs(nums, root.Left, i)
				}
			}
		}
	}

	dfs(&left, root.Left, 0)
	dfs(&leaves, root, 1)
	dfs(&right, root.Right, 2)

	ans = append(ans, left...)
	ans = append(ans, leaves...)
	for i := len(right) - 1; i >= 0; i-- {
		ans = append(ans, right[i])
	}

	return ans
}