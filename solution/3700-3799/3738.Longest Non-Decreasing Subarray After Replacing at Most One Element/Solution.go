func longestSubarray(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i], right[i] = 1, 1
	}

	for i := 1; i < n; i++ {
		if nums[i] >= nums[i-1] {
			left[i] = left[i-1] + 1
		}
	}

	for i := n - 2; i >= 0; i-- {
		if nums[i] <= nums[i+1] {
			right[i] = right[i+1] + 1
		}
	}

	ans := slices.Max(left)

	for i := 0; i < n; i++ {
		a := 0
		if i > 0 {
			a = left[i-1]
		}
		b := 0
		if i+1 < n {
			b = right[i+1]
		}
		if i > 0 && i+1 < n && nums[i-1] > nums[i+1] {
			ans = max(ans, max(a+1, b+1))
		} else {
			ans = max(ans, a+b+1)
		}
	}

	return ans
}
