func minimumMountainRemovals(nums []int) int {
	n := len(nums)
	left, right := make([]int, n), make([]int, n)
	for i := range left {
		left[i], right[i] = 1, 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[i] > nums[j] {
				left[i] = max(left[i], left[j]+1)
			}
		}
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if nums[i] > nums[j] {
				right[i] = max(right[i], right[j]+1)
			}
		}
	}
	ans := 0
	for i := range left {
		if left[i] > 1 && right[i] > 1 {
			ans = max(ans, left[i]+right[i]-1)
		}
	}
	return n - ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}