func findUnsortedSubarray(nums []int) int {
	n := len(nums)
	maxn, minn := math.MinInt32, math.MaxInt32
	left, right := -1, -1
	for i := 0; i < n; i++ {
		if maxn > nums[i] {
			right = i
		} else {
			maxn = nums[i]
		}
		if minn < nums[n-i-1] {
			left = n - i - 1
		} else {
			minn = nums[n-i-1]
		}
	}
	if right == -1 {
		return 0
	}
	return right - left + 1
}
