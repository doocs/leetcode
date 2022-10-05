func minTaps(n int, ranges []int) int {
	last := make([]int, n+1)
	for i, v := range ranges {
		l, r := max(0, i-v), min(n, i+v)
		last[l] = max(last[l], r)
	}
	ans, mx, pre := 0, 0, 0
	for i := 0; i < n; i++ {
		mx = max(mx, last[i])
		if mx <= i {
			return -1
		}
		if pre == i {
			ans++
			pre = mx
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}