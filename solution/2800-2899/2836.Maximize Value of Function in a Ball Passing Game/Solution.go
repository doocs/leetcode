func getMaxFunctionValue(receiver []int, k int64) (ans int64) {
	n, m := len(receiver), bits.Len(uint(k))
	f := make([][]int, n)
	g := make([][]int64, n)
	for i := range f {
		f[i] = make([]int, m)
		g[i] = make([]int64, m)
		f[i][0] = receiver[i]
		g[i][0] = int64(i)
	}
	for j := 1; j < m; j++ {
		for i := 0; i < n; i++ {
			f[i][j] = f[f[i][j-1]][j-1]
			g[i][j] = g[i][j-1] + g[f[i][j-1]][j-1]
		}
	}
	for i := 0; i < n; i++ {
		p := i
		t := int64(0)
		for j := 0; j < m; j++ {
			if k>>j&1 == 1 {
				t += g[p][j]
				p = f[p][j]
			}
		}
		ans = max(ans, t+int64(p))
	}
	return
}