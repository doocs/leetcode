func zigZagArrays(n int, l int, r int) (ans int) {
	const mod = int(1e9 + 7)
	m := r - l + 1
	up := make([]int, m)
	down := make([]int, m)
	for i := range up {
		up[i], down[i] = 1, 1
	}
	for k := 1; k < n; k++ {
		pre := make([]int, m+1)
		suf := make([]int, m+1)
		for i := 0; i < m; i++ {
			pre[i+1] = (pre[i] + down[i]) % mod
		}
		for i := m - 1; i >= 0; i-- {
			suf[i] = (suf[i+1] + up[i]) % mod
		}
		for i := 0; i < m; i++ {
			up[i] = pre[i]
			down[i] = suf[i+1]
		}
	}
	for i := 0; i < m; i++ {
		ans = (ans + up[i] + down[i]) % mod
	}
	return
}
