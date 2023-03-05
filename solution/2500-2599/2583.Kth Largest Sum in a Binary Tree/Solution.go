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
	q := []*TreeNode{root}
	for len(q) > 0 {
		t := 0
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			t += root.Val
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
		arr = append(arr, t)
	}
	if n := len(arr); n >= k {
		sort.Ints(arr)
		return int64(arr[n-k])
	}
	return -1
}