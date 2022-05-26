func minTimeToVisitAllPoints(points [][]int) int {
	res := 0
	for i := 1; i < len(points); i++ {
		x0, y0 := points[i-1][0], points[i-1][1]
		x1, y1 := points[i][0], points[i][1]
		res += max(abs(x0-x1), abs(y0-y1))
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func abs(a int) int {
	if a > 0 {
		return a
	}
	return -a
}