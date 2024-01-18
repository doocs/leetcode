func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	left, right := 1, n
	check := func(cnt int) bool {
		for i := 0; i < n+1-cnt; i++ {
			j := i + cnt - 1
			if nums[j]*cnt-(s[j+1]-s[i]) <= k {
				return true
			}
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}