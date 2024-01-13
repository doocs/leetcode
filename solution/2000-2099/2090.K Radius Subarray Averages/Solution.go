func getAverages(nums []int, k int) []int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	ans := make([]int, n)
	for i := 0; i < n; i++ {
		ans[i] = -1
		if i-k >= 0 && i+k < n {
			ans[i] = (s[i+k+1] - s[i-k]) / (k<<1 | 1)
		}
	}
	return ans
}