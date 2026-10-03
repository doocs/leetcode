func dieSimulator(n int, rollMax []int) int {
	const mod = 1e9 + 7
	f := make([][7][16]int, n+1)
	for j := 0; j < 7; j++ {
		for x := 0; x < 16; x++ {
			f[n][j][x] = 1
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 7; j++ {
			for x := 0; x < 16; x++ {
				ans := 0
				for k := 1; k <= 6; k++ {
					if k != j {
						ans += f[i+1][k][1]
					} else if x < rollMax[j-1] {
						ans += f[i+1][j][x+1]
					}
				}
				f[i][j][x] = ans % mod
			}
		}
	}
	return f[0][0][0]
}
