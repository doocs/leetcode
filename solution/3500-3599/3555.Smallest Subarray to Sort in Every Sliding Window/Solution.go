func minSubarraySort(nums []int, k int) []int {
	const inf = 1 << 30
	n := len(nums)
	f := func(i, j int) int {
		mi := inf
		mx := -inf
		l, r := -1, -1
		for p := i; p <= j; p++ {
			if nums[p] < mx {
				r = p
			} else {
				mx = nums[p]
			}
			q := j - p + i
			if nums[q] > mi {
				l = q
			} else {
				mi = nums[q]
			}
		}
		if r == -1 {
			return 0
		}
		return r - l + 1
	}

	ans := make([]int, 0, n-k+1)
	for i := 0; i <= n-k; i++ {
		ans = append(ans, f(i, i+k-1))
	}
	return ans
}
