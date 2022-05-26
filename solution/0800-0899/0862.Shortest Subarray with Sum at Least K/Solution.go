func shortestSubarray(nums []int, k int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	q := []int{0}
	ans := math.MaxInt32
	for i := 1; i <= n; i++ {
		for len(q) > 0 && s[i]-s[q[0]] >= k {
			ans = min(ans, i-q[0])
			q = q[1:]
		}
		for len(q) > 0 && s[i] <= s[q[len(q)-1]] {
			q = q[:len(q)-1]
		}
		q = append(q, i)
	}
	if ans == math.MaxInt32 {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}