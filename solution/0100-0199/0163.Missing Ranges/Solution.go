func findMissingRanges(nums []int, lower int, upper int) (ans [][]int) {
	n := len(nums)
	if n == 0 {
		return [][]int{{lower, upper}}
	}
	if nums[0] > lower {
		ans = append(ans, []int{lower, nums[0] - 1})
	}
	for i, b := range nums[1:] {
		if a := nums[i]; b-a > 1 {
			ans = append(ans, []int{a + 1, b - 1})
		}
	}
	if nums[n-1] < upper {
		ans = append(ans, []int{nums[n-1] + 1, upper})
	}
	return
}