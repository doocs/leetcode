func numberOfWays(corridor string) int {
	const mod = 1e9 + 7
	n := len(corridor)
	f := make([][3]int, n+1)
	f[n][2] = 1
	for i := n - 1; i >= 0; i-- {
		for k := 0; k < 3; k++ {
			nk := k
			if corridor[i] == 'S' {
				nk++
			}
			if nk > 2 {
				continue
			}
			f[i][k] = f[i+1][nk]
			if nk == 2 {
				f[i][k] = (f[i][k] + f[i+1][0]) % mod
			}
		}
	}
	return f[0][0]
}
