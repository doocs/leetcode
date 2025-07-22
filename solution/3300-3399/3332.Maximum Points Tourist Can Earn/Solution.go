func maxScore(n int, k int, stayScore [][]int, travelScore [][]int) (ans int) {
	f := make([][]int, k+1)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = math.MinInt32
		}
	}
	for j := 0; j < n; j++ {
		f[0][j] = 0
	}
	for i := 1; i <= k; i++ {
		for j := 0; j < n; j++ {
			f[i][j] = f[i-1][j] + stayScore[i-1][j]
			for h := 0; h < n; h++ {
				if h != j {
					f[i][j] = max(f[i][j], f[i-1][h]+travelScore[h][j])
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		ans = max(ans, f[k][j])
	}
	return
}
