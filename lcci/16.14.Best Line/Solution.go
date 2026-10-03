func bestLine(points [][]int) []int {
	n := len(points)
	ans := []int{0, 1}
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			if x1 == x2 && y1 == y2 {
				continue
			}
			cnt := 0
			a, b := -1, -1
			for k := 0; k < n; k++ {
				x3, y3 := points[k][0], points[k][1]
				c1 := (y2 - y1) * (x3 - x1)
				c2 := (y3 - y1) * (x2 - x1)
				if c1 == c2 {
					cnt++
					if a < 0 {
						a = k
					} else if b < 0 {
						b = k
					}
				}
			}
			if cnt > mx || (cnt == mx && (a < ans[0] || (a == ans[0] && b < ans[1]))) {
				mx = cnt
				ans[0], ans[1] = a, b
			}
		}
	}
	return ans
}
