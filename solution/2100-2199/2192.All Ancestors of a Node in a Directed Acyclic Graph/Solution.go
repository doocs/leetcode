func getAncestors(n int, edges [][]int) [][]int {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[1]] = append(g[e[1]], e[0])
	}
	var ans [][]int
	for i := 0; i < n; i++ {
		var t []int
		if len(g[i]) == 0 {
			ans = append(ans, t)
			continue
		}
		q := []int{i}
		vis := make([]bool, n)
		vis[i] = true
		for len(q) > 0 {
			for j := len(q); j > 0; j-- {
				v := q[0]
				q = q[1:]
				for _, u := range g[v] {
					if !vis[u] {
						vis[u] = true
						q = append(q, u)
						t = append(t, u)
					}
				}
			}
		}
		sort.Ints(t)
		ans = append(ans, t)
	}
	return ans
}