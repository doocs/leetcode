func arrangeCoins(n int) int {
	return int(math.Sqrt(2)*math.Sqrt(float64(n)+0.125) - 0.5)
}
