/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findMode(root *TreeNode) []int {
	mx, cnt, prev := 0, 0, 0
	has := false
	var ans []int
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, state := cur.node, cur.state
		if node == nil {
			continue
		}
		if state == 0 {
			stk = append(stk, frame{node, 1}, frame{node.Left, 0})
			continue
		}
		if has && prev == node.Val {
			cnt++
		} else {
			cnt = 1
		}
		if cnt > mx {
			ans = []int{node.Val}
			mx = cnt
		} else if cnt == mx {
			ans = append(ans, node.Val)
		}
		prev = node.Val
		has = true
		stk = append(stk, frame{node.Right, 0})
	}
	return ans
}
