/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minCameraCover(root *TreeNode) int {
	if root == nil {
		return 0
	}
	const inf = 1 << 29
	sub := map[*TreeNode][3]int{}
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node := cur.node
		if cur.state == 0 {
			stk = append(stk, frame{node, 1})
			if node.Right != nil {
				stk = append(stk, frame{node.Right, 0})
			}
			if node.Left != nil {
				stk = append(stk, frame{node.Left, 0})
			}
			continue
		}
		l := [3]int{inf, 0, 0}
		r := [3]int{inf, 0, 0}
		if node.Left != nil {
			l = sub[node.Left]
		}
		if node.Right != nil {
			r = sub[node.Right]
		}
		a := 1 + min(l[0], min(l[1], l[2])) + min(r[0], min(r[1], r[2]))
		b := min(l[0]+r[0], min(l[0]+r[1], l[1]+r[0]))
		c := l[1] + r[1]
		sub[node] = [3]int{a, b, c}
	}
	ans := sub[root]
	return min(ans[0], ans[1])
}
