/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func diameter(root *Node) int {
	if root == nil {
		return 0
	}
	g := map[*Node][]*Node{}
	seen := map[*Node]bool{root: true}
	stk := []*Node{root}
	for len(stk) > 0 {
		u := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		for _, child := range u.Children {
			if child == nil || seen[child] {
				continue
			}
			seen[child] = true
			g[u] = append(g[u], child)
			g[child] = append(g[child], u)
			stk = append(stk, child)
		}
	}
	farthest := func(start *Node) (int, *Node) {
		type frame struct {
			node *Node
			dist int
		}
		vis := map[*Node]bool{start: true}
		walk := []frame{{start, 0}}
		best, node := 0, start
		for len(walk) > 0 {
			f := walk[len(walk)-1]
			walk = walk[:len(walk)-1]
			if f.dist > best {
				best, node = f.dist, f.node
			}
			for _, v := range g[f.node] {
				if !vis[v] {
					vis[v] = true
					walk = append(walk, frame{v, f.dist + 1})
				}
			}
		}
		return best, node
	}
	_, nxt := farthest(root)
	ans, _ := farthest(nxt)
	return ans
}
