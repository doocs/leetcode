func isReflected(points [][]int) bool {
	const inf = 1 << 30
	minX, maxX := inf, -inf
	pointSet := map[[2]int]bool{}
	for _, p := range points {
		minX = min(minX, p[0])
		maxX = max(maxX, p[0])
		pointSet[[2]int{p[0], p[1]}] = true
	}
	s := minX + maxX
	for _, p := range points {
		if !pointSet[[2]int{s - p[0], p[1]}] {
			return false
		}
	}
	return true
}