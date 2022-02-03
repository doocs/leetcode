func findMinFibonacciNumbers(k int) int {
	if k < 2 {
		return k
	}
	a, b := 1, 1
	for b <= k {
		a, b = b, a+b
	}
	return 1 + findMinFibonacciNumbers(k-a)
}