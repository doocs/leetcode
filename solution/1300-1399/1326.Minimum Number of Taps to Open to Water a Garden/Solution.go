func minTaps(n int, ranges []int) (ans int) {
	last := make([]int, n+1)
	for i, x := range ranges {
		l, r := max(0, i-x), i+x
		last[l] = max(last[l], r)
	}
	var pre, mx int
	for i, j := range last[:n] {
		mx = max(mx, j)
		if mx <= i {
			return -1
		}
		if pre == i {
			ans++
			pre = mx
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}