func countTrapezoids(points [][]int) int {
	n := len(points)
	cnt1 := make(map[float64]map[float64]int, n*n)
	cnt2 := make(map[int]map[float64]int, n*n)

	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := 0; j < i; j++ {
			x2, y2 := points[j][0], points[j][1]
			dx, dy := x2-x1, y2-y1

			var k, b float64
			if dx == 0 {
				k = 1e9
				b = float64(x1)
			} else {
				k = float64(dy) / float64(dx)
				b = float64(int64(y1)*int64(dx)-int64(x1)*int64(dy)) / float64(dx)
			}

			if cnt1[k] == nil {
				cnt1[k] = make(map[float64]int)
			}
			cnt1[k][b]++

			p := (x1+x2+2000)*4000 + (y1 + y2 + 2000)
			if cnt2[p] == nil {
				cnt2[p] = make(map[float64]int)
			}
			cnt2[p][k]++
		}
	}

	ans := 0
	for _, e := range cnt1 {
		s := 0
		for _, t := range e {
			ans += s * t
			s += t
		}
	}
	for _, e := range cnt2 {
		s := 0
		for _, t := range e {
			ans -= s * t
			s += t
		}
	}
	return ans
}
