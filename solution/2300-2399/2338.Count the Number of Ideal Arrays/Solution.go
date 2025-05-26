func idealArrays(n int, maxValue int) (ans int) {
	const mod = int(1e9 + 7)
	c := make([][]int, n)
	for i := 0; i < n; i++ {
		c[i] = make([]int, 16)
		for j := 0; j <= i && j < 16; j++ {
			if j == 0 {
				c[i][j] = 1
			} else {
				c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
			}
		}
	}

	f := make([][16]int, maxValue+1)
	for i := 1; i <= maxValue; i++ {
		f[i][1] = 1
	}
	for j := 1; j < 15; j++ {
		for i := 1; i <= maxValue; i++ {
			for k := 2; k*i <= maxValue; k++ {
				f[k*i][j+1] = (f[k*i][j+1] + f[i][j]) % mod
			}
		}
	}

	for i := 1; i <= maxValue; i++ {
		for j := 1; j < 16; j++ {
			ans = (ans + f[i][j]*c[n-1][j-1]) % mod
		}
	}
	return
}
