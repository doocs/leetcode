func numPoints(darts [][]int, r int) int {
	n := len(darts)
	maxDarts := 1
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for _, c := range possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r) {
				maxDarts = max(maxDarts, countDarts(c[0], c[1], darts, r))
			}
		}
	}
	return maxDarts
}

func possibleCenters(x1, y1, x2, y2, r int) [][2]float64 {
	dx := float64(x2 - x1)
	dy := float64(y2 - y1)
	d := math.Sqrt(dx*dx + dy*dy)
	if d > 2*float64(r) {
		return nil
	}
	midX := float64(x1+x2) / 2
	midY := float64(y1+y2) / 2
	distToCenter := math.Sqrt(float64(r*r) - (d/2)*(d/2))
	offsetX := distToCenter * dy / d
	offsetY := distToCenter * -dx / d
	return [][2]float64{{midX + offsetX, midY + offsetY}, {midX - offsetX, midY - offsetY}}
}

func countDarts(x, y float64, darts [][]int, r int) (count int) {
	for _, dart := range darts {
		if math.Hypot(float64(dart[0])-x, float64(dart[1])-y) <= float64(r)+1e-7 {
			count++
		}
	}
	return
}
