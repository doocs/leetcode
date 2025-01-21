func maxValueOfCoins(piles [][]int, k int) int {
	n := len(piles)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		nums := piles[i-1]
		s := make([]int, len(nums)+1)
		for j := 1; j <= len(nums); j++ {
			s[j] = s[j-1] + nums[j-1]
		}

		for j := 0; j <= k; j++ {
			for h, w := range s {
				if j < h {
					break
				}
				f[i][j] = max(f[i][j], f[i-1][j-h]+w)
			}
		}
	}
	return f[n][k]
}
