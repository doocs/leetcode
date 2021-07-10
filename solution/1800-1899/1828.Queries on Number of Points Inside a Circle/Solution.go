func countPoints(points [][]int, queries [][]int) []int {
	ans := make([]int, len(queries))
	for i, query := range queries {
		x0, y0, r := query[0], query[1], query[2]
		for _, point := range points {
			x, y := point[0], point[1]
			dx, dy := x-x0, y-y0
			if dx*dx+dy*dy <= r*r {
				ans[i]++
			}
		}
	}
	return ans
}