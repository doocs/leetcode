func minReorder(n int, connections [][]int) (ans int) {
	g := make([][][2]int, n)
	for _, e := range connections {
		a, b := e[0], e[1]
		g[a] = append(g[a], [2]int{b, 1})
		g[b] = append(g[b], [2]int{a, 0})
	}
	q := []int{0}
	vis := make([]bool, n)
	vis[0] = true
	for len(q) > 0 {
		a := q[0]
		q = q[1:]
		for _, e := range g[a] {
			b, c := e[0], e[1]
			if !vis[b] {
				vis[b] = true
				q = append(q, b)
				ans += c
			}
		}
	}
	return
}
