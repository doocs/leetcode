func numWays(n int) int {
	if n == 0 {
		return 1
	}
	if n <= 2 {
		return n
	}
	a := make([]int, n)
	a[0] = 1
	a[1] = 2
	for i := 2; i < n; i++ {
		a[i] = (a[i-1] + a[i-2]) % 1000000007
	}
	return a[n-1]
}