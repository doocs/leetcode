func canAliceWin(n int) bool {
	x, k := 10, 0
	for n >= x {
		n -= x
		x--
		k++
	}
	return k%2 == 1
}
