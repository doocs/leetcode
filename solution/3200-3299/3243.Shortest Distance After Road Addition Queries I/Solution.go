func shortestDistanceAfterQueries(n int, queries [][]int) []int {
	g := make([][]int, n)
	for i := range g {
		g[i] = append(g[i], i+1)
	}
	bfs := func(i int) int {
		q := []int{i}
		vis := make([]bool, n)
		vis[i] = true
		for d := 0; ; d++ {
			for k := len(q); k > 0; k-- {
				u := q[0]
				if u == n-1 {
					return d
				}
				q = q[1:]
				for _, v := range g[u] {
					if !vis[v] {
						vis[v] = true
						q = append(q, v)
					}
				}
			}
		}
	}
	ans := make([]int, len(queries))
	for i, q := range queries {
		g[q[0]] = append(g[q[0]], q[1])
		ans[i] = bfs(0)
	}
	return ans
}
