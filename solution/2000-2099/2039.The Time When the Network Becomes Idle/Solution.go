func networkBecomesIdle(edges [][]int, patience []int) (ans int) {
	n := len(patience)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	q := []int{0}
	vis := make([]bool, n)
	vis[0] = true
	for d := 1; len(q) > 0; d++ {
		t := d * 2
		for i := len(q); i > 0; i-- {
			u := q[0]
			q = q[1:]
			for _, v := range g[u] {
				if !vis[v] {
					vis[v] = true
					q = append(q, v)
					ans = max(ans, (t-1)/patience[v]*patience[v]+t+1)
				}
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}