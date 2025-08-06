func maxCollectedFruits(fruits [][]int) int {
	n := len(fruits)
	const inf = 1 << 29
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -inf
		}
	}

	f[0][n-1] = fruits[0][n-1]
	for i := 1; i < n; i++ {
		for j := i + 1; j < n; j++ {
			f[i][j] = max(f[i-1][j], f[i-1][j-1]) + fruits[i][j]
			if j+1 < n {
				f[i][j] = max(f[i][j], f[i-1][j+1]+fruits[i][j])
			}
		}
	}

	f[n-1][0] = fruits[n-1][0]
	for j := 1; j < n; j++ {
		for i := j + 1; i < n; i++ {
			f[i][j] = max(f[i][j-1], f[i-1][j-1]) + fruits[i][j]
			if i+1 < n {
				f[i][j] = max(f[i][j], f[i+1][j-1]+fruits[i][j])
			}
		}
	}

	ans := f[n-2][n-1] + f[n-1][n-2]
	for i := 0; i < n; i++ {
		ans += fruits[i][i]
	}

	return ans
}