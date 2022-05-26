func maxProduct(nums []int) int {
	maxf, minf, res := nums[0], nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		m, n := maxf, minf
		maxf = max(nums[i], max(nums[i]*m, nums[i]*n))
		minf = min(nums[i], min(nums[i]*m, nums[i]*n))
		res = max(res, maxf)
	}
	return res
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