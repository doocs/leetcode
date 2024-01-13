/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func kthLargestLevelSum(root *TreeNode, k int) int64 {
	arr := []int{}
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		if len(arr) <= d {
			arr = append(arr, 0)
		}
		arr[d] += root.Val
		dfs(root.Left, d+1)
		dfs(root.Right, d+1)
	}

	dfs(root, 0)
	if n := len(arr); n >= k {
		sort.Ints(arr)
		return int64(arr[n-k])
	}
	return -1
}