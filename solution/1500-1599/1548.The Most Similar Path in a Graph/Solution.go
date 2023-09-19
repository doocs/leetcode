func mostSimilar(n int, roads [][]int, names []string, targetPath []string) []int {
	g := make([][]int, n)
	for _, r := range roads {
		a, b := r[0], r[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	m := len(targetPath)
	const inf = 1 << 30
	f := make([][]int, m)
	pre := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		pre[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = inf
			pre[i][j] = -1
		}
	}
	for j, s := range names {
		if targetPath[0] != s {
			f[0][j] = 1
		} else {
			f[0][j] = 0
		}
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			for _, k := range g[j] {
				t := f[i-1][k]
				if targetPath[i] != names[j] {
					t++
				}
				if t < f[i][j] {
					f[i][j] = t
					pre[i][j] = k
				}
			}
		}
	}
	mi, k := inf, 0
	for j := 0; j < n; j++ {
		if f[m-1][j] < mi {
			mi = f[m-1][j]
			k = j
		}
	}
	ans := make([]int, m)
	for i := m - 1; i >= 0; i-- {
		ans[i] = k
		k = pre[i][k]
	}
	return ans
}