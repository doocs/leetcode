func countComponents(n int, edges [][]int) int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	ans := 0
	for i := 0; i < n; i++ {
		if vis[i] {
			continue
		}
		ans++
		stk := []int{i}
		vis[i] = true
		for len(stk) > 0 {
			u := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			for _, v := range g[u] {
				if !vis[v] {
					vis[v] = true
					stk = append(stk, v)
				}
			}
		}
	}
	return ans
}
