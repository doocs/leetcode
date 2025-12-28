func maximumScore(nums []int) int64 {
	n := len(nums)
	suf := make([]int64, n)
	suf[n-1] = int64(nums[n-1])
	for i := n - 2; i >= 0; i-- {
		suf[i] = min(int64(nums[i]), suf[i+1])
	}
	var pre int64 = 0
	var ans int64 = math.MinInt64
	for i := 0; i < n-1; i++ {
		pre += int64(nums[i])
		ans = max(ans, pre-suf[i+1])
	}
	return ans
}
