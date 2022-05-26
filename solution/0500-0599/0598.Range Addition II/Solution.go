func maxCount(m int, n int, ops [][]int) int {
	for _, op := range ops {
		m = min(m, op[0])
		n = min(n, op[1])
	}
	return m * n
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}