func findSpecialNodes(n int, edges [][]int) string {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}

	bfs := func(start int) (int, []int) {
		dist := make([]int, n)
		for i := range dist {
			dist[i] = -1
		}
		dist[start] = 0
		q := make([]int, 0, n)
		q = append(q, start)
		far := start
		for head := 0; head < len(q); head++ {
			u := q[head]
			if dist[u] > dist[far] {
				far = u
			}
			for _, v := range g[u] {
				if dist[v] == -1 {
					dist[v] = dist[u] + 1
					q = append(q, v)
				}
			}
		}
		return far, dist
	}

	a, _ := bfs(0)
	b, dist1 := bfs(a)
	_, dist2 := bfs(b)
	d := dist1[b]

	ans := make([]byte, n)
	for i := range ans {
		ans[i] = '0'
	}
	for i := 0; i < n; i++ {
		if dist1[i] == d || dist2[i] == d {
			ans[i] = '1'
		}
	}
	return string(ans)
}
