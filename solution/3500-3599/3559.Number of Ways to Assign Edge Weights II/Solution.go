func assignEdgeWeights(edges [][]int, queries [][]int) []int {
	n := len(edges) + 1
	m := bits.Len(uint(n))
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, m)
	}
	p := make([]int, n+1)
	depth := make([]int, n+1)
	q := []int{1}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		f[i][0] = p[i]
		for j := 1; j < m; j++ {
			f[i][j] = f[f[i][j-1]][j-1]
		}
		for _, j := range g[i] {
			if j != p[i] {
				p[j] = i
				depth[j] = depth[i] + 1
				q = append(q, j)
			}
		}
	}
	const mod = int(1e9 + 7)
	pow2 := make([]int, n)
	pow2[0] = 1
	for i := 1; i < n; i++ {
		pow2[i] = pow2[i-1] * 2 % mod
	}
	ans := make([]int, len(queries))
	for i, qq := range queries {
		u, v := qq[0], qq[1]
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
		d := depth[u] + depth[v] - 2*depth[x]
		if d > 0 {
			ans[i] = pow2[d-1]
		}
	}
	return ans
}
