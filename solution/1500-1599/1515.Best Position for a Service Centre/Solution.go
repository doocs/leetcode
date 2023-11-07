func getMinDistSum(positions [][]int) float64 {
	n := len(positions)
	var x, y float64
	for _, p := range positions {
		x += float64(p[0])
		y += float64(p[1])
	}
	x /= float64(n)
	y /= float64(n)
	const decay float64 = 0.999
	const eps float64 = 1e-6
	var alpha float64 = 0.5
	for {
		var gradX, gradY float64
		var dist float64
		for _, p := range positions {
			a := x - float64(p[0])
			b := y - float64(p[1])
			c := math.Sqrt(a*a + b*b)
			gradX += a / (c + 1e-8)
			gradY += b / (c + 1e-8)
			dist += c
		}
		dx := gradX * alpha
		dy := gradY * alpha
		if math.Abs(dx) <= eps && math.Abs(dy) <= eps {
			return dist
		}
		x -= dx
		y -= dy
		alpha *= decay
	}
}