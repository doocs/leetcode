func countSubarrays(nums []int, k int64) int64 {
	n := len(nums)
	s := make([]int64, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + int64(v)
	}
	ans := 0
	for i := 1; i <= n; i++ {
		if s[i]-s[i-1] >= k {
			continue
		}
		left, right := 1, i
		for left < right {
			mid := (left + right + 1) >> 1
			if (s[i]-s[i-mid])*int64(mid) < k {
				left = mid
			} else {
				right = mid - 1
			}
		}
		ans += left
	}
	return int64(ans)
}