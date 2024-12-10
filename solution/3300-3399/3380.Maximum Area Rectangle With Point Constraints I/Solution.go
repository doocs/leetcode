func maxRectangleArea(points [][]int) int {
	check := func(x1, y1, x2, y2 int) bool {
		cnt := 0
		for _, point := range points {
			x, y := point[0], point[1]
			if x < x1 || x > x2 || y < y1 || y > y2 {
				continue
			}
			if (x == x1 || x == x2) && (y == y1 || y == y2) {
				cnt++
				continue
			}
			return false
		}
		return cnt == 4
	}

	ans := -1
	for i := 0; i < len(points); i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := 0; j < i; j++ {
			x2, y2 := points[j][0], points[j][1]
			x3, y3 := min(x1, x2), min(y1, y2)
			x4, y4 := max(x1, x2), max(y1, y2)
			if check(x3, y3, x4, y4) {
				ans = max(ans, (x4-x3)*(y4-y3))
			}
		}
	}
	return ans
}
