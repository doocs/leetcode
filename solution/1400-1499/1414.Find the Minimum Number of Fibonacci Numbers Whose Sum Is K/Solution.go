func findMinFibonacciNumbers(k int) (ans int) {
	a, b := 1, 1
	for b <= k {
		c := a + b
		a = b
		b = c
	}

	for k > 0 {
		if k >= b {
			k -= b
			ans++
		}
		c := b - a
		b = a
		a = c
	}
	return
}