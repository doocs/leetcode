var f = func() map[int]bool {
	f := map[int]bool{}
	a, b := 0, 1
	for b < 1000000001 {
		a, b = b, a+b
		f[b] = true
	}
	return f
}()

func threeFibonacciSum(n int) bool {
	return n%2 == 0 && f[n/2]
}
