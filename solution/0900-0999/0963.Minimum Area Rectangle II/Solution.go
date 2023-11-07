func minAreaFreeRect(points [][]int) float64 {
	n := len(points)
	f := func(x, y int) int {
		return x*40001 + y
	}
	s := map[int]bool{}
	for _, p := range points {
		s[f(p[0], p[1])] = true
	}
	ans := 1e20
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := 0; j < n; j++ {
			if j != i {
				x2, y2 := points[j][0], points[j][1]
				for k := j + 1; k < n; k++ {
					if k != i {
						x3, y3 := points[k][0], points[k][1]
						x4, y4 := x2-x1+x3, y2-y1+y3
						if s[f(x4, y4)] {
							if (x2-x1)*(x3-x1)+(y2-y1)*(y3-y1) == 0 {
								ww := (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)
								hh := (x3-x1)*(x3-x1) + (y3-y1)*(y3-y1)
								ans = math.Min(ans, math.Sqrt(float64(ww*hh)))
							}
						}
					}
				}
			}
		}
	}
	if ans == 1e20 {
		return 0
	}
	return ans
}