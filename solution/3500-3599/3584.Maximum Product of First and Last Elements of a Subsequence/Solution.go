func maximumProduct(nums []int, m int) int64 {
	ans := int64(math.MinInt64)
	mx := math.MinInt32
	mi := math.MaxInt32

	for i := m - 1; i < len(nums); i++ {
		x := nums[i]
		y := nums[i-m+1]
		mi = min(mi, y)
		mx = max(mx, y)
		ans = max(ans, max(int64(x)*int64(mi), int64(x)*int64(mx)))
	}

	return ans
}
