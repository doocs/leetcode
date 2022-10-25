func shortestSubarray(nums []int, k int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	q := []int{}
	ans := n + 1
	for i, v := range s {
		for len(q) > 0 && v-s[q[0]] >= k {
			ans = min(ans, i-q[0])
			q = q[1:]
		}
		for len(q) > 0 && s[q[len(q)-1]] >= v {
			q = q[:len(q)-1]
		}
		q = append(q, i)
	}
	if ans > n {
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