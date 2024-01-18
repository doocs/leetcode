func superEggDrop(k int, n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		f[i][1] = i
	}
	for i := 1; i <= n; i++ {
		for j := 2; j <= k; j++ {
			l, r := 1, i
			for l < r {
				mid := (l + r + 1) >> 1
				a, b := f[mid-1][j-1], f[i-mid][j]
				if a <= b {
					l = mid
				} else {
					r = mid - 1
				}
			}
			f[i][j] = max(f[l-1][j-1], f[i-l][j]) + 1
		}
	}
	return f[n][k]
}