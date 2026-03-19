func maxScore(nums1 []int, nums2 []int, K int) int64 {
	n, m := len(nums1), len(nums2)
	NEG := int64(math.MinInt64 / 4)
	f := make([][][]int64, n+1)
	for i := 0; i <= n; i++ {
		f[i] = make([][]int64, m+1)
		for j := 0; j <= m; j++ {
			f[i][j] = make([]int64, K+1)
			for k := 0; k <= K; k++ {
				f[i][j][k] = NEG
			}
		}
	}
	f[0][0][0] = 0
	for i := 0; i <= n; i++ {
		for j := 0; j <= m; j++ {
			for k := 0; k <= K; k++ {
				if i > 0 {
					f[i][j][k] = max(f[i][j][k], f[i-1][j][k])
				}
				if j > 0 {
					f[i][j][k] = max(f[i][j][k], f[i][j-1][k])
				}
				if i > 0 && j > 0 && k > 0 {
					f[i][j][k] = max(
						f[i][j][k],
						f[i-1][j-1][k-1]+int64(nums1[i-1])*int64(nums2[j-1]),
					)
				}
			}
		}
	}
	return f[n][m][K]
}
