func smallestRangeII(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	ans := nums[n-1] - nums[0]
	for i := 1; i < n; i++ {
		mi := min(nums[0]+k, nums[i]-k)
		mx := max(nums[i-1]+k, nums[n-1]-k)
		ans = min(ans, mx-mi)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}