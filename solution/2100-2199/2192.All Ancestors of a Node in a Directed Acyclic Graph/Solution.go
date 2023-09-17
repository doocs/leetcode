func getAncestors(n int, edges [][]int) [][]int {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}
	ans := make([][]int, n)
	bfs := func(s int) {
		q := []int{s}
		vis := make([]bool, n)
		vis[s] = true
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
					ans[j] = append(ans[j], s)
				}
			}
		}
	}
	for i := 0; i < n; i++ {
		bfs(i)
	}
	return ans
}