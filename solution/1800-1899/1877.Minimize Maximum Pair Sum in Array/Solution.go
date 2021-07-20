func minPairSum(nums []int) int {
	sort.Ints(nums)
	res, n := 0, len(nums)
	for i := 0; i < (n >> 1); i++ {
		res = max(res, nums[i]+nums[n-i-1])
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}