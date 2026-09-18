func findMedian(n int, edges [][]int, queries [][]int) []int {
	m := bits.Len(uint(n))
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, w})
		g[v] = append(g[v], [2]int{u, w})
	}
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}
	p := make([]int, n)
	depth := make([]int, n)
	dist := make([]int, n)
	q := []int{0}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		f[i][0] = p[i]
		for j := 1; j < m; j++ {
			f[i][j] = f[f[i][j-1]][j-1]
		}
		for _, nxt := range g[i] {
			j, w := nxt[0], nxt[1]
			if j != p[i] {
				p[j] = i
				depth[j] = depth[i] + 1
				dist[j] = dist[i] + w
				q = append(q, j)
			}
		}
	}
	ans := make([]int, len(queries))
	for i, qq := range queries {
		u, v := qq[0], qq[1]
		if u == v {
			ans[i] = u
			continue
		}
		x, y := u, v
		if depth[x] < depth[y] {
			x, y = y, x
		}
		for j := m - 1; j >= 0; j-- {
			if depth[x]-depth[y] >= 1<<j {
				x = f[x][j]
			}
		}
		for j := m - 1; j >= 0; j-- {
			if f[x][j] != f[y][j] {
				x, y = f[x][j], f[y][j]
			}
		}
		if x != y {
			x = p[x]
		}
		w := dist[u] + dist[v] - 2*dist[x]
		if 2*(dist[u]-dist[x]) >= w {
			cur := u
			for j := m - 1; j >= 0; j-- {
				k := f[cur][j]
				if depth[k] >= depth[x] && 2*(dist[u]-dist[k]) < w {
					cur = k
				}
			}
			ans[i] = p[cur]
		} else {
			cur := v
			for j := m - 1; j >= 0; j-- {
				k := f[cur][j]
				if depth[k] > depth[x] && 2*(dist[u]+dist[k]-2*dist[x]) >= w {
					cur = k
				}
			}
			ans[i] = cur
		}
	}
	return ans
}
