/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func amountOfTime(root *TreeNode, start int) int {
	g := map[int][]int{}
	type frame struct {
		node, fa *TreeNode
	}
	stk := []frame{{root, nil}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, fa := cur.node, cur.fa
		if node == nil {
			continue
		}
		if fa != nil {
			g[node.Val] = append(g[node.Val], fa.Val)
			g[fa.Val] = append(g[fa.Val], node.Val)
		}
		stk = append(stk, frame{node.Right, node}, frame{node.Left, node})
	}
	dist := map[int]int{}
	type step struct {
		node, fa, state int
	}
	walk := []step{{start, -1, 0}}
	for len(walk) > 0 {
		cur := walk[len(walk)-1]
		walk = walk[:len(walk)-1]
		node, fa, state := cur.node, cur.fa, cur.state
		nxts := g[node]
		if state == 0 {
			walk = append(walk, step{node, fa, 1})
			for i := len(nxts) - 1; i >= 0; i-- {
				if nxt := nxts[i]; nxt != fa {
					walk = append(walk, step{nxt, node, 0})
				}
			}
			continue
		}
		best := 0
		for _, nxt := range nxts {
			if nxt != fa {
				best = max(best, 1+dist[nxt])
			}
		}
		dist[node] = best
	}
	return dist[start]
}
