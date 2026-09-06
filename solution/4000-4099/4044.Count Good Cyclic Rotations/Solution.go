func countGoodRotations(nums []int) int {
	n := len(nums)
	m := n / 2

	var l, r int64
	for i := 0; i < m; i++ {
		l += int64(nums[i])
	}
	for i := m; i < n; i++ {
		r += int64(nums[i])
	}

	ans := 0
	if l > r {
		ans++
	}

	for i := 0; i < n-1; i++ {
		l -= int64(nums[i])
		r += int64(nums[i])
		l += int64(nums[(i+m)%n])
		r -= int64(nums[(i+m)%n])
		if l > r {
			ans++
		}
	}

	return ans
}
