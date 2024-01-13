func reachableNodes(n int, edges [][]int, restricted []int) int {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	for _, v := range restricted {
		vis[v] = true
	}
	q := []int{0}
	ans := 0
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		ans++
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				q = append(q, j)
			}
		}
	}
	return ans
}