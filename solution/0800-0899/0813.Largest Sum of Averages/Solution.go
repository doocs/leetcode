func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	f := make([][]float64, n)
	for i := range f {
		f[i] = make([]float64, k+1)
	}
	var dfs func(int, int) float64
	dfs = func(i, k int) float64 {
		if i == n {
			return 0
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		if k == 1 {
			return float64(s[n]-s[i]) / float64(n-i)
		}
		ans := 0.0
		for j := i + 1; j < n; j++ {
			ans = math.Max(ans, float64(s[j]-s[i])/float64(j-i)+dfs(j, k-1))
		}
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}
