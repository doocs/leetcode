func minOperationsQueries(n int, edges [][]int, queries [][]int) []int {
	m := bits.Len(uint(n))
	g := make([][][2]int, n)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}
	p := make([]int, n)
	cnt := make([][26]int, n)
	cnt[0] = [26]int{}
	depth := make([]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]-1
		g[u] = append(g[u], [2]int{v, w})
		g[v] = append(g[v], [2]int{u, w})
	}
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
				cnt[j] = [26]int{}
				for k := 0; k < 26; k++ {
					cnt[j][k] = cnt[i][k]
				}
				cnt[j][w]++
				depth[j] = depth[i] + 1
				q = append(q, j)
			}
		}
	}
	ans := make([]int, len(queries))
	for i, qq := range queries {
		u, v := qq[0], qq[1]
		x, y := u, v
		if depth[x] < depth[y] {
			x, y = y, x
		}
		for j := m - 1; j >= 0; j-- {
			if depth[x]-depth[y] >= (1 << j) {
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
		mx := 0
		for j := 0; j < 26; j++ {
			mx = max(mx, cnt[u][j]+cnt[v][j]-2*cnt[x][j])
		}
		ans[i] = depth[u] + depth[v] - 2*depth[x] - mx
	}
	return ans
}