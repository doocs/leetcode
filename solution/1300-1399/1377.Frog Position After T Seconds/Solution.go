type pid struct {
	x int
	p float64
}

func frogPosition(n int, edges [][]int, t int, target int) float64 {
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	q := []pid{pid{1, 1.0}}
	vis := make([]bool, n+1)
	vis[1] = true
	for len(q) > 0 && t >= 0 {
		for k := len(q); k > 0; k-- {
			x := q[0]
			q = q[1:]
			u, p := x.x, x.p
			var nxt []int
			for _, v := range g[u] {
				if !vis[v] {
					vis[v] = true
					nxt = append(nxt, v)
				}
			}
			if u == target && (len(nxt) == 0 || t == 0) {
				return p
			}
			for _, v := range nxt {
				q = append(q, pid{v, p / float64(len(nxt))})
			}
		}
		t--
	}
	return 0
}