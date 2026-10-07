func countPairs(n int, edges [][]int) (ans int64) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	dfs := func(i int) int {
		if vis[i] {
			return 0
		}
		vis[i] = true
		stk := []int{i}
		cnt := 0
		for len(stk) > 0 {
			u := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			cnt++
			for _, j := range g[u] {
				if !vis[j] {
					vis[j] = true
					stk = append(stk, j)
				}
			}
		}
		return cnt
	}
	var s int64
	for i := 0; i < n; i++ {
		t := int64(dfs(i))
		ans += s * t
		s += t
	}
	return
}
