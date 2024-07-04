func kClosest(points [][]int, k int) [][]int {
	sort.Slice(points, func(i, j int) bool {
		return math.Hypot(float64(points[i][0]), float64(points[i][1])) < math.Hypot(float64(points[j][0]), float64(points[j][1]))
	})
	return points[:k]
}
