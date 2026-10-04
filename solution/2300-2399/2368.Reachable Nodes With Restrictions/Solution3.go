func reachableNodes(n int, edges [][]int, restricted []int) int {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	for _, i := range restricted {
		vis[i] = true
	}
	ans := 0
	stk := []int{0}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if vis[i] {
			continue
		}
		vis[i] = true
		ans++
		for _, j := range g[i] {
			if !vis[j] {
				stk = append(stk, j)
			}
		}
	}
	return ans
}
