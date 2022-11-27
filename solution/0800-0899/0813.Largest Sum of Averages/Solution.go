func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	f := [110][110]float64{}
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	var dfs func(i, k int) float64
	dfs = func(i, k int) float64 {
		if i == n {
			return 0
		}
		if k == 1 {
			return float64(s[n]-s[i]) / float64(n-i)
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		var ans float64
		for j := i; j < n; j++ {
			t := float64(s[j+1]-s[i])/float64(j-i+1) + dfs(j+1, k-1)
			ans = math.Max(ans, t)
		}
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}