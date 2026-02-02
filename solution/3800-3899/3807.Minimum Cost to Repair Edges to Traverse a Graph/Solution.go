func minCost(n int, edges [][]int, k int) int {
	sort.Slice(edges, func(i, j int) bool {
		return edges[i][2] < edges[j][2]
	})

	check := func(idx int) bool {
		g := make([][]int, n)
		for i := 0; i <= idx; i++ {
			u, v := edges[i][0], edges[i][1]
			g[u] = append(g[u], v)
			g[v] = append(g[v], u)
		}

		q := make([]int, 0, n)
		q = append(q, 0)
		vis := make([]bool, n)
		vis[0] = true

		dist := 0
		for len(q) > 0 {
			nq := make([]int, 0)
			for _, u := range q {
				if u == n-1 {
					return dist <= k
				}
				for _, v := range g[u] {
					if !vis[v] {
						vis[v] = true
						nq = append(nq, v)
					}
				}
			}
			q = nq
			dist++
		}
		return false
	}

	m := len(edges)
	l, r := 0, m-1
	for l < r {
		mid := (l + r) >> 1
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	if check(l) {
		return edges[l][2]
	}
	return -1
}
