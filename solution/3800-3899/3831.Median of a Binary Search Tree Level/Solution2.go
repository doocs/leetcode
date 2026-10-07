/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func levelMedian(root *TreeNode, level int) int {
	nums := make([]int, 0)
	type frame struct {
		node  *TreeNode
		i     int
		state int
	}
	stk := []frame{{root, 0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, i, state := cur.node, cur.i, cur.state
		if node == nil {
			continue
		}
		if state == 0 {
			stk = append(stk, frame{node, i, 1}, frame{node.Left, i + 1, 0})
			continue
		}
		if i == level {
			nums = append(nums, node.Val)
		}
		stk = append(stk, frame{node.Right, i + 1, 0})
	}
	if len(nums) == 0 {
		return -1
	}
	return nums[len(nums)/2]
}
