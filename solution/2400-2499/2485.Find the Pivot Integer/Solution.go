func pivotInteger(n int) int {
	for x := 1; x <= n; x++ {
		if (1+x)*x == (x+n)*(n-x+1) {
			return x
		}
	}
	return -1
}