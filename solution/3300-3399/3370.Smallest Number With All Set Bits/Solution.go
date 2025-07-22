func smallestNumber(n int) int {
	x := 1
	for x-1 < n {
		x <<= 1
	}
	return x - 1
}
