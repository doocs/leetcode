func findLengthOfLCIS(nums []int) int {
	ans, cnt := 1, 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 1
		}
	}
	return ans
}