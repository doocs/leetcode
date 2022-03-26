func minimumHealth(damage []int, armor int) int64 {
	var s int64
	var mx int
	for _, v := range damage {
		s += int64(v)
		mx = max(mx, v)
	}
	return s - int64(min(mx, armor)) + 1
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