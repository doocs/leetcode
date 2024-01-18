func minScore(n int, roads [][]int) int {
	type pair struct{ i, v int }
	g := make([][]pair, n)
	for _, e := range roads {
		a, b, d := e[0]-1, e[1]-1, e[2]
		g[a] = append(g[a], pair{b, d})
		g[b] = append(g[b], pair{a, d})
	}
	vis := make([]bool, n)
	ans := 1 << 30
	q := []int{0}
	vis[0] = true
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			for _, nxt := range g[i] {
				j, d := nxt.i, nxt.v
				ans = min(ans, d)
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
	}
	return ans
}