func judgeSquareSum(c int) bool {
	n := int(math.Sqrt(float64(c)))
	for i := 2; i <= n; i++ {
		if c%i == 0 {
			exp := 0
			for c%i == 0 {
				c /= i
				exp++
			}
			if i%4 == 3 && exp%2 != 0 {
				return false
			}
		}
	}
	return c%4 != 3
}
