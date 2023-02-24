func minTimeToVisitAllPoints(points [][]int) (ans int) {
	for i, p := range points[1:] {
		dx := abs(p[0] - points[i][0])
		dy := abs(p[1] - points[i][1])
		ans += max(dx, dy)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}