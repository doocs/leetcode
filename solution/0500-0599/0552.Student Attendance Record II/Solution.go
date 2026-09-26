func checkRecord(n int) int {
	const mod = int(1e9 + 7)
	f := [2][3]int{{1, 1, 1}, {1, 1, 1}}
	for i := 0; i < n; i++ {
		var g [2][3]int
		for j := 0; j < 2; j++ {
			for k := 0; k < 3; k++ {
				ans := f[j][0]
				if j == 0 {
					ans = (ans + f[1][0]) % mod
				}
				if k < 2 {
					ans = (ans + f[j][k+1]) % mod
				}
				g[j][k] = ans % mod
			}
		}
		f = g
	}
	return f[0][0]
}
