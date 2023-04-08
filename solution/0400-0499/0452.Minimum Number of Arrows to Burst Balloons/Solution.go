func findMinArrowShots(points [][]int) (ans int) {
	sort.Slice(points, func(i, j int) bool { return points[i][1] < points[j][1] })
	last := -(1 << 60)
	for _, p := range points {
		a, b := p[0], p[1]
		if a > last {
			ans++
			last = b
		}
	}
	return
}