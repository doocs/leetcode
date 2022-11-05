func findMissingRanges(nums []int, lower int, upper int) (ans []string) {
	f := func(a, b int) string {
		if a == b {
			return strconv.Itoa(a)
		}
		return strconv.Itoa(a) + "->" + strconv.Itoa(b)
	}
	n := len(nums)
	if n == 0 {
		ans = append(ans, f(lower, upper))
		return
	}
	if nums[0] > lower {
		ans = append(ans, f(lower, nums[0]-1))
	}
	for i := 1; i < n; i++ {
		a, b := nums[i-1], nums[i]
		if b-a > 1 {
			ans = append(ans, f(a+1, b-1))
		}
	}
	if nums[n-1] < upper {
		ans = append(ans, f(nums[n-1]+1, upper))
	}
	return
}