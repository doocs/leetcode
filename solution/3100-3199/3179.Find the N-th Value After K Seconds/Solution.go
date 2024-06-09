func valueAfterKSeconds(n int, k int) int {
	const mod int = 1e9 + 7
	a := make([]int, n)
	for i := range a {
		a[i] = 1
	}
	for ; k > 0; k-- {
		for i := 1; i < n; i++ {
			a[i] = (a[i] + a[i-1]) % mod
		}
	}
	return a[n-1]
}