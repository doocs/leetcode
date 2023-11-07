func insertBits(N int, M int, i int, j int) int {
	for k := i; k <= j; k++ {
		N &= ^(1 << k)
	}
	return N | M<<i
}