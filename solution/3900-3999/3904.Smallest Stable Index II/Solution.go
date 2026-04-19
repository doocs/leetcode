func firstStableIndex(nums []int, k int) int {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]

	for i := n - 2; i >= 0; i-- {
		right[i] = min(right[i+1], nums[i])
	}

	left := 0
	for i, x := range nums {
		left = max(left, x)
		if left-right[i] <= k {
			return i
		}
	}
	return -1
}
