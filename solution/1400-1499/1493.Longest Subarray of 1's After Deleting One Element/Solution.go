func longestSubarray(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := 1; i < n; i++ {
		if nums[i-1] == 1 {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 2; i >= 0; i-- {
		if nums[i+1] == 1 {
			right[i] = right[i+1] + 1
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		ans = max(ans, left[i]+right[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}