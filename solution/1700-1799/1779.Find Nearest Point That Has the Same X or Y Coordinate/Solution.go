func nearestValidPoint(x int, y int, points [][]int) int {
	ans, mi := -1, 1000000
	for i, p := range points {
		a, b := p[0], p[1]
		if a == x || b == y {
			d := abs(a-x) + abs(b-y)
			if d < mi {
				ans, mi = i, d
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}