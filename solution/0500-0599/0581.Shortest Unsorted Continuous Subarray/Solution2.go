func findUnsortedSubarray(nums []int) int {
	const inf = 1 << 30
	n := len(nums)
	l, r := -1, -1
	mi, mx := inf, -inf
	for i, x := range nums {
		if mx > x {
			r = i
		} else {
			mx = x
		}
		if mi < nums[n-i-1] {
			l = n - i - 1
		} else {
			mi = nums[n-i-1]
		}
	}
	if r == -1 {
		return 0
	}
	return r - l + 1
}