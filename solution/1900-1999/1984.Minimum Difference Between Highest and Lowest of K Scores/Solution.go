func minimumDifference(nums []int, k int) int {
	sort.Ints(nums)
	ans := 100000
	for i := 0; i < len(nums)-k+1; i++ {
		ans = min(ans, nums[i+k-1]-nums[i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}