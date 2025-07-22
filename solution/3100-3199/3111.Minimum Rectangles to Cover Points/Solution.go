func minRectanglesToCoverPoints(points [][]int, w int) (ans int) {
	sort.Slice(points, func(i, j int) bool { return points[i][0] < points[j][0] })
	x1 := -1
	for _, p := range points {
		if x := p[0]; x > x1 {
			ans++
			x1 = x + w
		}
	}
	return
}
