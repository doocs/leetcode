func countSubarrays(nums []int, k int64) (ans int64) {
	n := len(nums)
	s := make([]int64, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + int64(x)
	}
	for i := 1; i <= n; i++ {
		l, r := 0, i
		for l < r {
			mid := (l + r + 1) >> 1
			if (s[i]-s[i-mid])*int64(mid) < k {
				l = mid
			} else {
				r = mid - 1
			}
		}
		ans += int64(l)
	}
	return
}