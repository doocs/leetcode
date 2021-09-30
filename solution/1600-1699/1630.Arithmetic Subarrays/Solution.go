func checkArithmeticSubarrays(nums []int, l []int, r []int) []bool {
	n := len(l)
	var res []bool
	for i := 0; i < n; i++ {
		res = append(res, check(nums, l[i], r[i]))
	}
	return res
}

func check(nums []int, l, r int) bool {
	if r-l < 2 {
		return true
	}
	s := make(map[int]bool)
	mx, mi := -100010, 100010
	for i := l; i <= r; i++ {
		s[nums[i]] = true
		mx = max(mx, nums[i])
		mi = min(mi, nums[i])
	}
	if (mx-mi)%(r-l) != 0 {
		return false
	}
	delta := (mx - mi) / (r - l)
	for i := 1; i <= r-l; i++ {
		if !s[mi+delta*i] {
			return false
		}
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}