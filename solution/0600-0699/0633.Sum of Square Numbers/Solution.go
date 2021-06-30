func judgeSquareSum(c int) bool {
	i, j := 0, int(math.Sqrt(float64(c)))
	for i <= j {
		s := i*i + j*j
		if s < c {
			i++
		} else if s > c {
			j--
		} else {
			return true
		}
	}
	return false
}