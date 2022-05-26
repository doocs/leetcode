func networkBecomesIdle(edges [][]int, patience []int) int {
	n := len(patience)
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	q := []int{0}
	vis[0] = true
	ans, step := 0, 0
	for len(q) > 0 {
		step++
		for i := len(q); i > 0; i-- {
			u := q[0]
			q = q[1:]
			for _, v := range g[u] {
				if vis[v] {
					continue
				}
				vis[v] = true
				q = append(q, v)
				d, t := step*2, patience[v]
				ans = max(ans, (d-1)/t*t+d+1)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}