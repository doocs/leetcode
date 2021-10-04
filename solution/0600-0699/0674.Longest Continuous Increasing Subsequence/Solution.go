func findLengthOfLCIS(nums []int) int {
	res, f := 1, 1
	for i := 1; i < len(nums); i++ {
		if nums[i-1] < nums[i] {
			f += 1
			res = max(res, f)
		} else {
			f = 1
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}