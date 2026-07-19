func maximumValue(n int, s int, m int) int64 {
	if n == 1 {
		return int64(s)
	}
	return int64(s) + int64(n/2)*int64(m-1) + 1
}
