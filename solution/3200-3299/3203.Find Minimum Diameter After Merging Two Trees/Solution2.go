func minimumDiameterAfterMerge(edges1 [][]int, edges2 [][]int) int {
	d1 := treeDiameter(edges1)
	d2 := treeDiameter(edges2)
	return max(d1, d2, (d1+1)/2+(d2+1)/2+1)
}

func treeDiameter(edges [][]int) int {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	farthest := func(start int) (int, int) {
		ans, node := 0, start
		stk := [][3]int{{start, -1, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			i, fa, t := cur[0], cur[1], cur[2]
			if ans < t {
				ans, node = t, i
			}
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, [3]int{j, i, t + 1})
				}
			}
		}
		return ans, node
	}
	_, a := farthest(0)
	ans, _ := farthest(a)
	return ans
}
