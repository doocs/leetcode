func gridGame(grid [][]int) int64 {
	ans := math.MaxInt64
	s1, s2 := 0, 0
	for _, v := range grid[0] {
		s1 += v
	}
	for j, v := range grid[0] {
		s1 -= v
		ans = min(ans, max(s1, s2))
		s2 += grid[1][j]
	}
	return int64(ans)
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