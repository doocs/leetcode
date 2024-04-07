func longestMonotonicSubarray(nums []int) int {
	ans := 1
	t := 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
	}
	t = 1
	for i, x := range nums[1:] {
		if nums[i] > x {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
	}
	return ans
}