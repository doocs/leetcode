func countComponents(n int, edges [][]int) (ans int) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	for i := range g {
		if vis[i] {
			continue
		}
		vis[i] = true
		ans++
		q := []int{i}
		for len(q) > 0 {
			a := q[0]
			q = q[1:]
			for _, b := range g[a] {
				if !vis[b] {
					vis[b] = true
					q = append(q, b)
				}
			}
		}
	}
	return
}