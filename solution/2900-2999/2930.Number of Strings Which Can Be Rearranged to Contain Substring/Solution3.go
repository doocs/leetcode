func stringCount(n int) int {
	const mod int = 1e9 + 7
	var f [2][3][2]int
	f[1][2][1] = 1
	for i := 0; i < n; i++ {
		var g [2][3][2]int
		for l := 0; l < 2; l++ {
			for e := 0; e < 3; e++ {
				for t := 0; t < 2; t++ {
					a := f[l][e][t] * 23
					b := f[min(1, l+1)][e][t]
					c := f[l][min(2, e+1)][t]
					d := f[l][e][min(1, t+1)]
					g[l][e][t] = (a + b + c + d) % mod
				}
			}
		}
		f = g
	}
	return f[0][0][0]
}
