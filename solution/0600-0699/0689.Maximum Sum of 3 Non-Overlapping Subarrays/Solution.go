func maxSumOfThreeSubarrays(nums []int, k int) (ans []int) {
	n := len(nums)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + nums[i]
	}

	pre := make([][]int, n)
	suf := make([][]int, n)

	for i, t, idx := 0, 0, 0; i < n-k+1; i++ {
		cur := s[i+k] - s[i]
		if cur > t {
			pre[i+k-1] = []int{cur, i}
			t, idx = cur, i
		} else {
			pre[i+k-1] = []int{t, idx}
		}
	}

	for i, t, idx := n-k, 0, 0; i >= 0; i-- {
		cur := s[i+k] - s[i]
		if cur >= t {
			suf[i] = []int{cur, i}
			t, idx = cur, i
		} else {
			suf[i] = []int{t, idx}
		}
	}

	for i, t := k, 0; i < n-2*k+1; i++ {
		cur := s[i+k] - s[i] + pre[i-1][0] + suf[i+k][0]
		if cur > t {
			ans = []int{pre[i-1][1], i, suf[i+k][1]}
			t = cur
		}
	}

	return
}