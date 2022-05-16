func findMaxValueOfEquation(points [][]int, k int) int {
	q := [][]int{}
	ans := math.MinInt32
	for _, p := range points {
		x, y := p[0], p[1]
		for len(q) > 0 && x-q[0][0] > k {
			q = q[1:]
		}
		if len(q) > 0 {
			ans = max(ans, y+x+q[0][1]-q[0][0])
		}
		for len(q) > 0 && y-x > q[len(q)-1][1]-q[len(q)-1][0] {
			q = q[:len(q)-1]
		}
		q = append(q, p)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}