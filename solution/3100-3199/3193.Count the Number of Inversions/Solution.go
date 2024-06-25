func numberOfPermutations(n int, requirements [][]int) int {
	req := make([]int, n)
	for i := range req {
		req[i] = -1
	}
	for _, r := range requirements {
		req[r[0]] = r[1]
	}
	if req[0] > 0 {
		return 0
	}
	req[0] = 0
	m := slices.Max(req)
	const mod = int(1e9 + 7)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m+1)
	}
	f[0][0] = 1
	for i := 1; i < n; i++ {
		l, r := 0, m
		if req[i] >= 0 {
			l, r = req[i], req[i]
		}
		for j := l; j <= r; j++ {
			for k := 0; k <= min(i, j); k++ {
				f[i][j] = (f[i][j] + f[i-1][j-k]) % mod
			}
		}
	}
	return f[n-1][req[n-1]]
}