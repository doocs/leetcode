func minCost(nums []int) int64 {
	n := len(nums)
	ans := int64(math.MaxInt64)
	l := 0
	for r, x := range nums {
		if r == n-1 || x != nums[r+1] {
			if r > 0 && l < n-1 {
				ans = min(ans, int64(l+n-r-1)*int64(x))
			}
			l = r + 1
		}
	}
	return ans
}
