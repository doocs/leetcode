func minFlipsMonoIncr(s string) int {
	n := len(s)
	left, right := make([]int, n+1), make([]int, n+1)
	ans := math.MaxInt32
	for i := 1; i <= n; i++ {
		left[i] = left[i-1]
		if s[i-1] == '1' {
			left[i]++
		}
	}
	for i := n - 1; i >= 0; i-- {
		right[i] = right[i+1]
		if s[i] == '0' {
			right[i]++
		}
	}
	for i := 0; i <= n; i++ {
		ans = min(ans, left[i]+right[i])
	}
	return ans
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
