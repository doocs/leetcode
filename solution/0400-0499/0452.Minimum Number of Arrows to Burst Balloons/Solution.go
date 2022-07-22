func findMinArrowShots(points [][]int) int {
	sort.Slice(points, func(i, j int) bool { return points[i][1] < points[j][1] })
	ans := 1
	x := points[0][1]
	for _, v := range points {
		a, b := v[0], v[1]
		if a > x {
			ans++
			x = b
		}
	}
	return ans
}