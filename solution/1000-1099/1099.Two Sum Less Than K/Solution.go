func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	i, j := 0, len(nums)-1
	for i < j {
		if t := nums[i] + nums[j]; t < k {
			ans = max(ans, t)
			i++
		} else {
			j--
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}