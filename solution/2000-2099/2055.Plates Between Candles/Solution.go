func platesBetweenCandles(s string, queries [][]int) []int {
	n := len(s)
	presum := make([]int, n+1)
	for i := range s {
		if s[i] == '*' {
			presum[i+1] = 1
		}
		presum[i+1] += presum[i]
	}
	left, right := make([]int, n), make([]int, n)
	for i, l := 0, -1; i < n; i++ {
		if s[i] == '|' {
			l = i
		}
		left[i] = l
	}
	for i, r := n-1, -1; i >= 0; i-- {
		if s[i] == '|' {
			r = i
		}
		right[i] = r
	}
	ans := make([]int, len(queries))
	for k, q := range queries {
		i, j := right[q[0]], left[q[1]]
		if i >= 0 && j >= 0 && i < j {
			ans[k] = presum[j] - presum[i+1]
		}
	}
	return ans
}