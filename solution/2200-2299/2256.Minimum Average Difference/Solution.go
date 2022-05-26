func minimumAverageDifference(nums []int) int {
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + nums[i]
	}
	ans := 0
	mi := math.MaxInt32
	for i := 0; i < n; i++ {
		a := s[i] / (i + 1)
		b := 0
		if i != n-1 {
			b = (s[n-1] - s[i]) / (n - i - 1)
		}
		t := abs(a - b)
		if mi > t {
			ans = i
			mi = t
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}