func kClosest(points [][]int, k int) [][]int {
	sort.Slice(points, func(i, j int) bool {
		a, b := points[i], points[j]
		return a[0]*a[0]+a[1]*a[1] < b[0]*b[0]+b[1]*b[1]
	})
	return points[:k]
}