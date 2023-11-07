func cutSquares(square1 []int, square2 []int) []float64 {
	x1, y1 := float64(square1[0])+float64(square1[2])/2, float64(square1[1])+float64(square1[2])/2
	x2, y2 := float64(square2[0])+float64(square2[2])/2, float64(square2[1])+float64(square2[2])/2
	if x1 == x2 {
		y3 := math.Min(float64(square1[1]), float64(square2[1]))
		y4 := math.Max(float64(square1[1]+square1[2]), float64(square2[1]+square2[2]))
		return []float64{x1, y3, x2, y4}
	}
	k := (y2 - y1) / (x2 - x1)
	b := y1 - k*x1
	if math.Abs(k) > 1 {
		y3 := math.Min(float64(square1[1]), float64(square2[1]))
		x3 := (y3 - b) / k
		y4 := math.Max(float64(square1[1]+square1[2]), float64(square2[1]+square2[2]))
		x4 := (y4 - b) / k
		if x3 > x4 || (x3 == x4 && y3 > y4) {
			return []float64{x4, y4, x3, y3}
		}
		return []float64{x3, y3, x4, y4}
	} else {
		x3 := math.Min(float64(square1[0]), float64(square2[0]))
		y3 := k*x3 + b
		x4 := math.Max(float64(square1[0]+square1[2]), float64(square2[0]+square2[2]))
		y4 := k*x4 + b
		return []float64{x3, y3, x4, y4}
	}
}