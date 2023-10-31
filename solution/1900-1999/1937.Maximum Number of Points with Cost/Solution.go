func maxPoints(points [][]int) (ans int64) {
	n := len(points[0])
	const inf int64 = 1e18
	f := make([]int64, n)
	for _, p := range points {
		g := make([]int64, n)
		lmx, rmx := -inf, -inf
		for j := range p {
			lmx = max(lmx, f[j]+int64(j))
			g[j] = max(g[j], int64(p[j])+lmx-int64(j))
		}
		for j := n - 1; j >= 0; j-- {
			rmx = max(rmx, f[j]-int64(j))
			g[j] = max(g[j], int64(p[j])+rmx+int64(j))
		}
		f = g
	}
	for _, x := range f {
		ans = max(ans, x)
	}
	return
}