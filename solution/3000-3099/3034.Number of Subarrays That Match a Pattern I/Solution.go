func countMatchingSubarrays(nums []int, pattern []int) (ans int) {
	f := func(a, b int) int {
		if a == b {
			return 0
		}
		if a < b {
			return 1
		}
		return -1
	}
	n, m := len(nums), len(pattern)
	for i := 0; i < n-m; i++ {
		ok := 1
		for k := 0; k < m && ok == 1; k++ {
			if f(nums[i+k], nums[i+k+1]) != pattern[k] {
				ok = 0
			}
		}
		ans += ok
	}
	return
}