/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructMaximumBinaryTree(nums []int) *TreeNode {
	type frame struct {
		l, r, side int
		parent     *TreeNode
	}
	n := len(nums)
	var root *TreeNode
	stk := []frame{{0, n - 1, 0, nil}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if cur.l > cur.r {
			continue
		}
		i := cur.l
		for j := cur.l; j <= cur.r; j++ {
			if nums[j] > nums[i] {
				i = j
			}
		}
		node := &TreeNode{Val: nums[i]}
		if cur.parent == nil {
			root = node
		} else if cur.side == 0 {
			cur.parent.Left = node
		} else {
			cur.parent.Right = node
		}
		stk = append(stk, frame{i + 1, cur.r, 1, node}, frame{cur.l, i - 1, 0, node})
	}
	return root
}
