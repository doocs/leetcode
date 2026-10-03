/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findFrequentTreeSum(root *TreeNode) (ans []int) {
	cnt := map[int]int{}
	sub := map[*TreeNode]int{}
	mx := 0
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
			stk = append(stk, frame{node, 1})
			if node.Right != nil {
				stk = append(stk, frame{node.Right, 0})
			}
			if node.Left != nil {
				stk = append(stk, frame{node.Left, 0})
			}
			continue
		}
		l, r := 0, 0
		if node.Left != nil {
			l = sub[node.Left]
		}
		if node.Right != nil {
			r = sub[node.Right]
		}
		s := node.Val + l + r
		cnt[s]++
		mx = max(mx, cnt[s])
		sub[node] = s
	}
	for k, v := range cnt {
		if v == mx {
			ans = append(ans, k)
		}
	}
	return
}
