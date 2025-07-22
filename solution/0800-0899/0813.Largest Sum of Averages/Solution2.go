func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	f := make([][]float64, n+1)
	for i := range f {
		f[i] = make([]float64, k+1)
	}
	for i := 1; i <= n; i++ {
		f[i][1] = float64(s[i]) / float64(i)
		for j := 2; j <= min(i, k); j++ {
			for h := 0; h < i; h++ {
				f[i][j] = max(f[i][j], f[h][j-1]+float64(s[i]-s[h])/float64(i-h))
			}
		}
	}
	return f[n][k]
}
