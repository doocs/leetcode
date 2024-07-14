func maxFrequency(nums []int, k int) int {
	n := len(nums)
	sort.Ints(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	check := func(m int) bool {
		for i := m; i <= n; i++ {
			if nums[i-1]*m-(s[i]-s[i-m]) <= k {
				return true
			}
		}
		return false
	}
	l, r := 1, n
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}