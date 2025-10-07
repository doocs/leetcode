func splitArray(nums []int) int64 {
	n := len(nums)
	s := make([]int64, n)
	f := make([]bool, n)
	g := make([]bool, n)
	for i := range f {
		f[i] = true
		g[i] = true
	}

	s[0] = int64(nums[0])
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + int64(nums[i])
		f[i] = f[i-1]
		if nums[i] <= nums[i-1] {
			f[i] = false
		}
	}
	for i := n - 2; i >= 0; i-- {
		g[i] = g[i+1]
		if nums[i] <= nums[i+1] {
			g[i] = false
		}
	}

	const inf = int64(^uint64(0) >> 1)
	ans := inf
	for i := 0; i < n-1; i++ {
		if f[i] && g[i+1] {
			s1 := s[i]
			s2 := s[n-1] - s[i]
			ans = min(ans, abs(s1-s2))
		}
	}
	if ans < inf {
		return ans
	}
	return -1
}

func abs(x int64) int64 {
	if x < 0 {
		return -x
	}
	return x
}
