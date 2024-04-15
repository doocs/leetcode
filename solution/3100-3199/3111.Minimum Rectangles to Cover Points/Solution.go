func minRectanglesToCoverPoints(points [][]int, w int) (ans int) {
	sort.Slice(points, func(i, j int) bool { return points[i][0] < points[j][0] })
	x1 := -(1 << 30)
	for _, p := range points {
		if x := p[0]; x1+w < x {
			x1 = x
			ans++
		}
	}
	return
}