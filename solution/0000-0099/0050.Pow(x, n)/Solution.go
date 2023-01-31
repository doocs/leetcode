func myPow(x float64, n int) float64 {
	if n >= 0 {
		return qmi(x, n)
	}
	return 1.0 / qmi(x, -n)
}

func qmi(a float64, k int) float64 {
	var res float64 = 1
	for k != 0 {
		if k&1 == 1 {
			res *= a
		}
		a *= a
		k >>= 1
	}
	return res
}