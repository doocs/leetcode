func internalAngles(sides []int) []float64 {
	sort.Ints(sides)
	a, b, c := sides[0], sides[1], sides[2]
	if a+b <= c {
		return []float64{}
	}
	A := math.Acos(float64(b*b+c*c-a*a)/float64(2*b*c)) * 180 / math.Pi
	B := math.Acos(float64(a*a+c*c-b*b)/float64(2*a*c)) * 180 / math.Pi
	C := 180 - A - B
	return []float64{A, B, C}
}