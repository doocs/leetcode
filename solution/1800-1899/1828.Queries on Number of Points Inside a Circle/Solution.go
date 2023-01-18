func countPoints(points [][]int, queries [][]int) (ans []int) {
	for _, q := range queries {
		x, y, r := q[0], q[1], q[2]
		cnt := 0
		for _, p := range points {
			i, j := p[0], p[1]
			dx, dy := i-x, j-y
			if dx*dx+dy*dy <= r*r {
				cnt++
			}
		}
		ans = append(ans, cnt)
	}
	return
}