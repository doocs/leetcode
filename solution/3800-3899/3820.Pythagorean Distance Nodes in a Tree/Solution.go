func specialNodes(n int, edges [][]int, x int, y int, z int) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	const inf = int(1e9)

	bfs := func(i int) []int {
		dist := make([]int, n)
		for k := 0; k < n; k++ {
			dist[k] = inf
		}
		q := make([]int, 0)
		dist[i] = 0
		q = append(q, i)
		for len(q) > 0 {
			sz := len(q)
			for ; sz > 0; sz-- {
				u := q[0]
				q = q[1:]
				for _, v := range g[u] {
					if dist[v] > dist[u]+1 {
						dist[v] = dist[u] + 1
						q = append(q, v)
					}
				}
			}
		}
		return dist
	}

	d1 := bfs(x)
	d2 := bfs(y)
	d3 := bfs(z)

	ans := 0
	for i := 0; i < n; i++ {
		a := []int{d1[i], d2[i], d3[i]}
		sort.Ints(a)
		x0, x1, x2 := int64(a[0]), int64(a[1]), int64(a[2])
		if x0*x0+x1*x1 == x2*x2 {
			ans++
		}
	}
	return ans
}
