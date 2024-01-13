func dieSimulator(n int, rollMax []int) (ans int) {
	f := make([][7][16]int, n+1)
	for j := 1; j <= 6; j++ {
		f[1][j][1] = 1
	}
	const mod = 1e9 + 7
	for i := 2; i <= n; i++ {
		for j := 1; j <= 6; j++ {
			for x := 1; x <= rollMax[j-1]; x++ {
				for k := 1; k <= 6; k++ {
					if k != j {
						f[i][k][1] = (f[i][k][1] + f[i-1][j][x]) % mod
					} else if x+1 <= rollMax[j-1] {
						f[i][j][x+1] = (f[i][j][x+1] + f[i-1][j][x]) % mod
					}
				}
			}
		}
	}
	for j := 1; j <= 6; j++ {
		for x := 1; x <= rollMax[j-1]; x++ {
			ans = (ans + f[n][j][x]) % mod
		}
	}
	return
}