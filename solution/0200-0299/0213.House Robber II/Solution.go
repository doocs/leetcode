func rob(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	s1, s2 := robRange(nums, 0, n-2), robRange(nums, 1, n-1)
	return max(s1, s2)
}

func robRange(nums []int, l, r int) int {
	a, b := 0, nums[l]
	for i := l + 1; i <= r; i++ {
		a, b = b, max(nums[i]+a, b)
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}